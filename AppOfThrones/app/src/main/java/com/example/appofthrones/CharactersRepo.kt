package com.example.appofthrones

import kotlin.random.Random

object CharactersRepo {
    val characters: MutableList<Character> = mutableListOf()
        get() {
            if (field.isEmpty()) {
                field.addAll(dummyCharacters())
            }

            return field
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
            quote = "Actor ${int}",
            father = "Frase ${int}",
            mother = "Madre ${int}",
            spouse = "Espos@ ${int}",
            house = dummyHouse()
        )
    }

    private fun dummyHouse(): House {
        val ids = arrayOf("stark", "lannister", "tyrell", "arryn", "martell", "baratheon", "greyjoy", "frey", "tully")
        val randomIdPosition =  Random.nextInt(ids.size)

        return House(name = ids[randomIdPosition], region = "Region", words = "Lema")
    }
}