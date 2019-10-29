// herencia
open class Organism(val name: String) {
    // bloque de inicializacion
    init {
        print("Born!\n")
    }
    
    open fun eat() {
        print("Yummy!\n")
    }
}


// clase y constructor primario
class Person(name: String): Organism(name) {
    
    // constructor secundario
    constructor(name: String, lastName: String): this(name) {
        
    }
    
    fun greeting(to: String): String {
        return "Hello " + to + ",my name is " + name + "\n"
    }
    
    // sobreescritura metodos
    override fun eat() {
        super.eat()
        print("Veggie food!\n")
    }
    
}

class Plant(name: String): Organism(name) {
    // sobreescritura metodos
    override fun eat() {
        print("Phtosyntesis!\n")
    }
}

fun main(args: Array<String>) {
    // lambda
    val greeting: (String) -> Unit = { name ->
        print("Hello " + name)                                    
    }
    
    greeting.invoke("John")
}