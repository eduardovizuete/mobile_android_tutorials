package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.message)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener { toast("Hello ${editText.text}")}
    }

    fun toast(message: String): Unit {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
