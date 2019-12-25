package de.yellowhing.shared

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var p = Person()
        name = p.name
        Log.d("p before name", "ss"+p.name);
        p.name = "jake"
        Log.d("p after name", "sssss"+p.name);

        Log.d("p before sex", "ss"+p.sex);
        p.sex = true
        Log.d("p after sex","ss" + p.sex);

        Log.d("p before age", "ss"+p.age);
        p.age = 18
        Log.d("p after age","ss" + p.age);

        Log.d("p before accout", "ss"+p.accout);
        p.accout = 66.66f
        Log.d("p after accout","ss" + p.accout);

        Log.d("p before id", "ss"+p.id);
        p.id = 666L
        Log.d("p after id","ss" + p.id);

        Log.d("p before id", "ss"+p.names);

        val hh = HashSet<String>()
        hh.add("aaa")
        hh.add("bbb")
        p.names = hh
        Log.d("p after id","ss" + p.names);


        name = p.name
        Log.d("p before name", "ssss"+p.name);
        p.name = null
        Log.d("p after name","ssss" + p.name);


        Log.d("p before id", "ssss"+p.names);
        p.names = null
        Log.d("p after id","ssss" + p.names);
    }
}
