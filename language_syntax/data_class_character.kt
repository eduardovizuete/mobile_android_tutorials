// data class y metodos
data class Character(var name: String, var title: String) {
    
    fun fight() {
        println("Arghhhh!")
    }
} 

fun main(args: Array<String>) {
    val jonSnow: Character = Character("Jon Snow", "Lord comandante de la guardia nocturna")
    
    println(jonSnow.toString())
    jonSnow.fight()
}
