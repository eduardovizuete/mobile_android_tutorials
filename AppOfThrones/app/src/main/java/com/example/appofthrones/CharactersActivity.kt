package com.example.appofthrones

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_characters)

        val list: RecyclerView = findViewById(R.id.list)
        val adapter: CharactersAdapter = CharactersAdapter()

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        val characters: MutableList<Character> = CharactersRepo.characters
        adapter.setCharacters(characters)
    }

    fun showDetails(button: View) {
        Log.d("CharactersActivity", "Click is working")
        val intent: Intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}

