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
    val urlList : List<String> = items
        .filter { it.type == MediaItem.Type.PHOTO }
        .sortedBy { it.title }
        .map { it.thumbUrl }

    // mutable list
    val mutableList: MutableList<Int> = mutableListOf(2, 3, 5)
    mutableList.add(5)
    mutableList.remove(2)
}
