package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val adapter = MediaAdapter() { navigateToDetail(it) }

    val recyclerView by lazy { findViewById(R.id.recycler) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        MediaProvider.dataAsync { adapter.items = it }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_all -> Filter.None
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> null
        }

        filter?.let {
            GlobalScope.launch(Dispatchers.Main) {
                val media1 = withContext(Dispatchers.IO) { MediaProvider.dataSync("cats") }
                // if the sentence above is too long, is posible to extract to suspend function, for instance getData
                val media2 = getData("nature")
                updateData(media1 + media2, filter)
            }

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // it is posible extract to suspend function
    private suspend fun getData(type: String): List<MediaItem> = withContext(Dispatchers.IO) {
        MediaProvider.dataSync(type)
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

}

sealed class Filter {
    object None : Filter()
    class ByType(val type: MediaItem.Type) : Filter()
}