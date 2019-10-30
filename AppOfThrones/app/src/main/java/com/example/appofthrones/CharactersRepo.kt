package com.example.appofthrones

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
            house = House(
                name = "Casa ${int}",
                region = "Región ${int}",
                words = "Lema ${int}"
            )
        )
    }
}