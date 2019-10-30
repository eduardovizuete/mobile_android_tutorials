// data class y metodos
data class Character(var name: String = "José Nieves", val title: String) {
    
    fun fight() {
        println("Arghhhh!")
    }
} 

fun main(args: Array<String>) {
    val jonSnow: Character = Character(title = "Lord comandante de la guardia nocturna")
    
    jonSnow.fight()
}
