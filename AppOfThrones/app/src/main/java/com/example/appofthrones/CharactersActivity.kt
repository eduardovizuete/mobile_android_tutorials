package com.example.appofthrones

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_characters)
    }

    fun showDetails(button: View) {
        Log.d("CharactersActivity", "Click is working")
        val intent: Intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}

