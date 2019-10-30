package com.example.appofthrones

object CharactersRepo {
    var characters: MutableList<Character> = mutableListOf()

    private fun dummyCharacters(): MutableList<Character> {
        val dummies: MutableList<Character> = mutableListOf()

        for (index in 1 ..10) {
            val character: Character = Character(
                name = "Personaje ${index}",
                title =  "Título ${index}",
                born =  "Nació en ${index}",
                actor = "Actor ${index}",
                quote = "Actor ${index}",
                father = "Frase ${index}",
                mother = "Madre ${index}",
                spouse = "Espos@ ${index}",
                house = House(
                    name = "Casa ${index}",
                    region = "Región ${index}",
                    words = "Lema ${index}"
                )
            )

            dummies.add(character)
        }

        return dummies
    }
}