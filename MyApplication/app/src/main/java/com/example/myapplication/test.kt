package com.example.myapplication

data class Person(val name: String, val age: Int)

fun test(list: List<Person>) {
    for ((name, age) in list) {
        print(name)
        print(age)
    }
}