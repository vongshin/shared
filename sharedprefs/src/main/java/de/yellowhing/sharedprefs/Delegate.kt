package de.yellowhing.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmName

class Delegate {
    companion object {
       private fun getSharedPrefs(context: Context, thisRef: Any?): SharedPreferences {
            if (thisRef == null) return PreferenceManager.getDefaultSharedPreferences(context);
            val sharedPrefsAnnotation = thisRef::class.findAnnotation<SharedPrefs>()
            var name: String = thisRef::class.jvmName
            var mode: Int = 0
            if (sharedPrefsAnnotation != null) {
                name = sharedPrefsAnnotation.name
                mode = sharedPrefsAnnotation.mode
            }
            return context.getSharedPreferences(name, mode)
        }

        private fun getKey(property: KProperty<*>):String{
            var key: String = property.name
            val alias = property.findAnnotation<Alias>()
            if (alias != null) {
                key = alias.key
            }
            return key;
        }
    }
    class OpString {
        var context: Context
        var defValue: String = ""

        constructor(context: Context) {
            this.context = context
        }

        constructor(context: Context, defValue: String) {
            this.context = context
            this.defValue = defValue
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            val key: String = getKey(property)
            return getSharedPrefs(context, thisRef).getString(key, defValue)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            val key: String = getKey(property)
            getSharedPrefs(context, thisRef)
                    .edit()
                    .putString(key, value)
                    .apply()
        }
    }
    class OpInt {
        var context: Context
        var defValue: Int = 0

        constructor(context: Context) {
            this.context = context
        }

        constructor(context: Context, defValue: Int) {
            this.context = context
            this.defValue = defValue
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            val key: String = getKey(property)
            return getSharedPrefs(context, thisRef).getInt(key, defValue)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            val key: String = getKey(property)
            getSharedPrefs(context, thisRef)
                    .edit()
                    .putInt(key, value)
                    .apply()
        }
    }

    class OpLong {
        var context: Context
        var defValue: Long = 0L

        constructor(context: Context) {
            this.context = context
        }

        constructor(context: Context, defValue: Long) {
            this.context = context
            this.defValue = defValue
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
            val key: String = getKey(property)
            return getSharedPrefs(context, thisRef).getLong(key, defValue)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
            val key: String = getKey(property)
            getSharedPrefs(context, thisRef)
                    .edit()
                    .putLong(key, value)
                    .apply()
        }
    }
    class OpFloat {
        var context: Context
        var defValue: Float = 0.0f

        constructor(context: Context) {
            this.context = context
        }

        constructor(context: Context, defValue: Float) {
            this.context = context
            this.defValue = defValue
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): Float {
            val key: String = getKey(property)
            return getSharedPrefs(context, thisRef).getFloat(key, defValue)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
            val key: String = getKey(property)
            getSharedPrefs(context, thisRef)
                    .edit()
                    .putFloat(key, value)
                    .apply()
        }
    }
    class OpBoolean {
        var context: Context
        var defValue: Boolean = false

        constructor(context: Context) {
            this.context = context
        }

        constructor(context: Context, defValue: Boolean) {
            this.context = context
            this.defValue = defValue
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
            val key: String = getKey(property)
            return getSharedPrefs(context, thisRef).getBoolean(key, defValue)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            val key: String = getKey(property)
            getSharedPrefs(context, thisRef)
                    .edit()
                    .putBoolean(key, value)
                    .apply()
        }
    }
}
