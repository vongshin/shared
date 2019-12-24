package de.yellowhing.sharedprefs

import android.app.AppGlobals
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.jvmName

class Preference<T> : ReadWriteProperty<Any, T> {
    private var defValue: T? = null
    private val context: Context;

    constructor(defValue: T? = null){
        this.defValue = null
        this.context = AppGlobals.getInitialApplication()
    }
    constructor(defValue: T? = null, context: Context = AppGlobals.getInitialApplication()){
        this.defValue = defValue
        this.context = context
    }
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val sp = getSharedPrefs(context, thisRef)
        val key = getKey(property)
        when(property.returnType.classifier){
            Boolean::class -> {
                defValue = if(defValue == null) defaultBooleanValue as T else defValue
                return sp.getBoolean(key, defValue as Boolean) as T
            }
            Int::class -> {
                defValue = if(defValue == null) defaultIntValue as T else defValue
                return sp.getInt(key, defValue as Int) as T
            }
            Float::class -> {
                defValue = if(defValue == null) defaultFloatValue as T else defValue
                return sp.getFloat(key, defValue as Float) as T
            }
            Long::class ->{
                defValue = if(defValue == null) defaultLongValue as T else defValue
                return sp.getLong(key, defValue as Long) as T
            }
            String::class ->{
                if(defValue == null){
                    return sp.getString(key, defaultStringValue) as T
                }else{
                    return sp.getString(key, defValue as String) as T
                }
            }
            isSuperclassOf(property.returnType.classifier as KClass<*>) -> {
                if(defValue == null){
                    return sp.getStringSet(key, defaultSetStringValue) as T
                }else{
                    return sp.getStringSet(key, defValue as Set<String>) as T
                }
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }

    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val sp = getSharedPrefs(context, thisRef)
        val key = getKey(property)
        when(property.returnType.classifier){
            Boolean::class -> { sp.edit().putBoolean(key, value as Boolean).apply() }
            Int::class -> { sp.edit().putInt(key, value as Int).apply() }
            Float::class -> { sp.edit().putFloat(key, value as Float).apply() }
            Long::class -> { sp.edit().putLong(key, value as Long).apply() }
            String::class ->{ sp.edit().putString(key, value as String).apply()}
            isSuperclassOf(property.returnType.classifier as KClass<*>) -> {
                sp.edit().putStringSet(key, value as Set<String>).apply()
            }
            else ->{ throw UnsupportedOperationException()}
        }
    }

    companion object{
        val defaultBooleanValue: Boolean = false
        val defaultIntValue: Int = 0
        val defaultFloatValue: Float = 0.0f
        val defaultLongValue: Long = 0L
        val defaultStringValue: String? = null
        val defaultSetStringValue: Set<String>? = null
        /**
         * find sharedPrefs
         */
        private fun getSharedPrefs(context: Context, thisRef: Any?): SharedPreferences {
            if (thisRef == null) return PreferenceManager.getDefaultSharedPreferences(context);
            val sharedPrefsAnnotation = thisRef::class.findAnnotation<SharedPrefs>()
            var name: String = thisRef::class.jvmName
            var mode = 0
            if (sharedPrefsAnnotation != null) {
                name = sharedPrefsAnnotation.name
                mode = sharedPrefsAnnotation.mode
            }
            return context.getSharedPreferences(name, mode)
        }

        /**
         * find sharedPrefs key
         */
        private fun getKey(property: KProperty<*>): String{
            var key: String = property.name
            val alias = property.findAnnotation<Alias>()
            if (alias != null) {
                key = alias.key
            }
            return key;
        }

        fun isSuperclassOf(derived: KClass<*>): KClass<*>?{
            return if(Set::class.isSuperclassOf(derived)){
                Set::class
            }else{
                null
            }
        }
    }
}