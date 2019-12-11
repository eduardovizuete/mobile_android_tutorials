package com.example.myapplication

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random

object MediaProvider {

    private const val thumbBase = "http://lorempixel.com/400/400/"
    private val rnd = Random(1)

    private var data = emptyList<MediaItem>()

    private fun randomType() =
        rnd.nextInt(2).let { if (it == 0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO }

    fun dataAsync(type: String = "cats", f: (List<MediaItem>) -> Unit) = doAsync {
        if (data.isEmpty()) {
            data = dataSync(type)
        }

        uiThread {
            f(data)
        }
    }

    fun dataSync(type: String): List<MediaItem> {
        Thread.sleep(5000)
        return (1..10).map {
            MediaItem(it, "Title $it", "${thumbBase}$type/$it", randomType())
        }
    }

}