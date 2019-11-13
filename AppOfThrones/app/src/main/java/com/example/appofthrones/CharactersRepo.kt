package com.example.appofthrones

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

const val URL_CHARACTERS = "http://5dcb2f6734d54a0014314d23.mockapi.io/characters"

object CharactersRepo {
    private var characters: MutableList<Character> = mutableListOf();

    fun requestCharacters(context: Context,
                          success: ((MutableList<Character>) -> Unit),
                          error: (() -> Unit)) {

        if (characters.isEmpty()) {

            val request = JsonArrayRequest(Request.Method.GET, URL_CHARACTERS, null,
                { response ->
                    characters = parseCharacter(response)
                    success.invoke(characters)
                },
                { volleyError ->
                    error.invoke()
                })

            Volley.newRequestQueue(context)
                .add(request)
        } else {
            success.invoke(characters)
        }
    }

    private fun parseCharacter(jsonArray: JSONArray): MutableList<Character> {
        val characters = mutableListOf<Character>()
        for (index in 0..(jsonArray.length() - 1)) {
            val character = parseCharacter(jsonArray.getJSONObject(index))
            characters.add(character)
        }

        return characters
    }

    private fun parseCharacter(jsonCharacter: JSONObject): Character {
        return Character(
            jsonCharacter.getString("id"),
            jsonCharacter.getString("name"),
            jsonCharacter.getString("born"),
            jsonCharacter.getString("title"),
            jsonCharacter.getString("actor"),
            jsonCharacter.getString("quote"),
            jsonCharacter.getString("father"),
            jsonCharacter.getString("mother"),
            jsonCharacter.getString("spouse"),
            parseHouse(jsonCharacter.getJSONObject("house"))
        )
    }

    private fun parseHouse(jsonHouse: JSONObject): House {
        return House(
            jsonHouse.getString("name"),
            jsonHouse.getString("region"),
            jsonHouse.getString("words")
        )
    }

    private fun dummyCharacters(): MutableList<Character> {
        // operador funcional
        return (1..10).map {
            intToCharacter(it)
        }.toMutableList()
    }

    fun findCharacterById(id: String): Character? {
        return characters.find { character ->
            character.id == id
        }
    }

    private fun intToCharacter(int: Int): Character {
        return Character(
            name = "Personaje ${int}",
            title = "Título ${int}",
            born = "Nació en ${int}",
            actor = "Actor ${int}",
            quote = "Frase ${int}",
            father = "Padre ${int}",
            mother = "Madre ${int}",
            spouse = "Espos@ ${int}",
            house = dummyHouse()
        )
    }

    private fun dummyHouse(): House {
        val ids = arrayOf(
            "stark",
            "lannister",
            "tyrell",
            "arryn",
            "martell",
            "baratheon",
            "greyjoy",
            "frey",
            "tully"
        )
        val randomIdPosition = Random.nextInt(ids.size)

        return House(name = ids[randomIdPosition], region = "Region", words = "Lema")
    }
}