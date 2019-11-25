package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Mensaje")
    }

    fun toast(message: String): Unit {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun getInt(int: Int) = 2 * int
}
