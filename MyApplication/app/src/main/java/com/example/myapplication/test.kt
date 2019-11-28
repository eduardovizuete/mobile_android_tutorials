package com.example.myapplication

open class Person(name: String = "Peter", val age: Int = 10) {
    // get - set optional methods
    var name: String = name
        get() = "Name: $field"
        set(value) {
            if (value != field) {
                field = value
            }
        }
}

class Developer(name: String) : Person(name, 20) {

}

// class with more one constructors
class DeveloperJunior : Person {
    constructor(name: String, age: Int) : super(name)
    constructor(age: Int): super(age = age)
}

fun test() {
    val person = Person("John", 30)
    val age = person.age
    val name = person.name

    // Person with default parameter values
    val p2 = Person()
    // Person with name and default parameter age
    val p3 = Person("Tom")
    // Person with age and default parameter name
    val p4 = Person(age = 20)
    // Person with age and name parameter in diferent order
    val p5 = Person(age = 20, name = "Thomas")
}
