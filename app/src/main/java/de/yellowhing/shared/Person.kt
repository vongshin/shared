package de.yellowhing.shared

import android.content.Context
import de.yellowhing.sharedprefs.Alias
import de.yellowhing.sharedprefs.Delegate
import de.yellowhing.sharedprefs.SharedPrefs

@SharedPrefs("person", Context.MODE_PRIVATE)
class Person constructor(context: Context){
    @Alias("name")
    var name:String by Delegate.OpString(context)

    var nameNick:String by Delegate.OpString(context, "nick")

    var age :Int by Delegate.OpInt(context,6)

    var sex : Boolean by Delegate.OpBoolean(context, true)

    var accout : Float by Delegate.OpFloat(context, 0.0f)

    var id :Long by Delegate.OpLong(context, 1L)
}