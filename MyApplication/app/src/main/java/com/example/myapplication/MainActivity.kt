package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    private val adapter = MediaAdapter() { navigateToDetail(it) }

    val recyclerView by lazy { findViewById(R.id.recycler) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        recyclerView.adapter = adapter
        MediaProvider.dataAsync { updateData(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_all -> Filter.None
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> null
        }

        filter?.let {
            launch {
                val media1 = async(Dispatchers.IO, CoroutineStart.LAZY) { MediaProvider.dataSync("cats") }
                val media2 = async(Dispatchers.IO) { MediaProvider.dataSync("nature") }
                val media3 = useAsync()
                updateData(media1.await() + media2.await(), filter)
            }

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
        adapter.items = when (filter) {
            Filter.None -> media
            is Filter.ByType -> media.filter { it.type == filter.type }
        }
    }

    private fun navigateToDetail(item: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.ID to item.id)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}

sealed class Filter {
    object None : Filter()
    class ByType(val type: MediaItem.Type) : Filter()
}