package de.yellowhing.shared

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var p = Person(this)
        Log.d("p before", p.name);
        p.name = "huangxing"
        Log.d("p after", p.name);
    }
}
