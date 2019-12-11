package com.example.myapplication

fun test() {
    val sum: (Int, Int) -> Int = { a, b -> a + b }
    val f1 = { a: Int, b: Int -> a + b }
    applyOp(2, 3, sum) // 5

    val mul = { a: Int, b: Int -> a * b }
    applyOp(2, 3, mul) // 6

    applyOp(2, 3) { first, second -> first - second }
}

fun applyOp(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)


// example callback with interfaces
interface Callback {
    fun onCallback(result: String)
}

fun doAsync(x: Int, callback: Callback) {
    // background
    callback.onCallback("finished")
}

fun testMainInterfaceCallback() {
    doAsync(20, object : Callback {
        override fun onCallback(result: String) {
            print(result)
        }
    })
}

// example callback with lambdas
fun doAsyncLambda(x: Int, callback: (String) -> Unit) {
    // background
    callback("finished")
}

fun testMainLambdaCallback() {
    doAsyncLambda(20) { result -> print(result) }
}

fun testCollection(items: List<MediaItem>) {
    val urlList: List<String> = items
        .filter { it.type == MediaItem.Type.PHOTO }
        .sortedBy { it.title }
        .map { it.thumbUrl }

    // mutable list
    val mutableList: MutableList<Int> = mutableListOf(2, 3, 5)
    mutableList.add(5)
    mutableList.remove(2)
}

fun testInfixFunctions() {
    val sum = 9 addition 10
    val pair = Pair(10, "Ten")
    val pair2 = 10 to "Ten"
}

infix fun Int.addition(other: Int) = this + other

// nullability
fun testNull() {
    val myInt: Int? = null

    // operator elvis ?:
    val notNullInt: Int = myInt ?: 0
    val myLong: Long = myInt?.toLong() ?: 0L
    // the next sentence can be replace with the above sentence
    val myLong1: Long = if (myInt != null) myInt.toLong() else 0L

}