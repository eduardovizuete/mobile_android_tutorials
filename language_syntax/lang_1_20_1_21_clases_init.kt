// clase y constructor primario
class Person(var name: String) {
    
    // constructor secundario
    constructor(name: String, lastName: String): this(name) {
        
    }
    
    // bloque de inicializacion
    init {
        print("Born!
")
    }
    
    fun greeting() {
        print("Hello world, my name is " + name + "
")
    }
    
}

fun main(args: Array<String>) {
    val john: Person = Person("John", "Johnson")
    
    john.greeting()
    print(john.name)
}