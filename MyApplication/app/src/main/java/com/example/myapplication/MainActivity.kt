package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Hello")
        toast("Hello", Toast.LENGTH_LONG)
        applicationContext.toast("Message")
    }

}
