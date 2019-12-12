package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val adapter = MediaAdapter { itemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        recycler.adapter = adapter
        progress.show()

        loadContent()
    }

    private fun loadContent(filter: Filter = Filter.None) = launch {
        val cats = async(Dispatchers.IO) { MediaProvider.dataSync("cats") }
        val nature = async(Dispatchers.IO) { MediaProvider.dataSync("nature") }
        updateData(cats.await() + nature.await(), filter)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_all -> Filter.None
            R.id.filter_photos -> Filter.ByMediaType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByMediaType(MediaItem.Type.VIDEO)
            else -> null
        }

        filter?.let {
            progress.show()
            loadContent(filter)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // it is posible extract to suspend function
    private suspend fun getData(type: String): List<MediaItem> = withContext(Dispatchers.IO) {
        MediaProvider.dataSync(type)
    }

    // transform old callback paradign callbacks to coroutines way - suspended functions
    suspend fun useAsync(): List<MediaItem> = suspendCancellableCoroutine { continuation ->
        MediaProvider.dataAsync { media ->
            continuation.resume(media)
        }
    }

    private fun updateData(media: List<MediaItem>, filter: Filter = Filter.None) {
        adapter.data = when (filter) {
            Filter.None -> media
            is Filter.ByMediaType -> media.filter { it.type == filter.type }
        }
        progress.hide()
    }

    private fun itemClicked(it: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}
