// herencia
open class Organism(val name: String) {
    // bloque de inicializacion
    init {
        print("Born!\n")
    }
}


// clase y constructor primario
class Person(name: String): Organism(name) {
    
    // constructor secundario
    constructor(name: String, lastName: String): this(name) {
        
    }
    
    fun greeting() {
        print("Hello world, my name is " + name + "\n")
    }
    
}

fun main(args: Array<String>) {
    val john: Person = Person("John", "Johnson")
    
    john.greeting()
    print(john.name)
}
