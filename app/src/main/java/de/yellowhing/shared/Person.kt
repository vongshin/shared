package de.yellowhing.shared

import android.content.Context
import de.yellowhing.sharedprefs.Alias
import de.yellowhing.sharedprefs.Preference
import de.yellowhing.sharedprefs.SharedPrefs

@SharedPrefs("person", Context.MODE_PRIVATE)
class Person {
    var sex: Boolean by Preference()
    var age: Int by Preference()
    var accout: Float by Preference()
    var id  by Preference<Long>()
    @Alias("name")
    var name by Preference<String>()
    var names: Set<String>? by Preference()
}