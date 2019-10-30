package com.example.appofthrones

object CharactersRepo {
    var characters: MutableList<Character> = mutableListOf()

    private fun dummyCharacters(): MutableList<Character> {
        // operadores funcionales
        /*val dummies: MutableList<Character> = (1..10).map { index ->
            intToCharacter(index)
        }.toMutableList()

        return dummies*/

        // opcion corta operador funcional
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