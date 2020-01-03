package com.example.myapplication.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.app
import com.example.myapplication.detail.DetailActivity
import com.example.myapplication.hide
import com.example.myapplication.main.di.MainModule
import com.example.myapplication.model.Filter
import com.example.myapplication.model.MediaItem
import com.example.myapplication.show
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter
    private val adapter =
        MediaAdapter { itemClicked(it) }
    private val component by lazy { app.component.plus(MainModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        recycler.adapter = adapter
        presenter.onCreate()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_all -> Filter.None
            R.id.filter_photos -> Filter.ByMediaType(
                MediaItem.Type.PHOTO
            )
            R.id.filter_videos -> Filter.ByMediaType(
                MediaItem.Type.VIDEO
            )
            else -> null
        }

        filter?.let {
            presenter.filterClicked(filter)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateData(media: List<MediaItem>) {
        adapter.data = media
    }

    private fun itemClicked(item: MediaItem) {
        presenter.itemClicked(item)
    }

    override fun showProgress() = progress.show()
    override fun hideProgress() = progress.hide()
    override fun navigateTo(id: Long) = startActivity<DetailActivity>(
        DetailActivity.EXTRA_ID to id
    )

}
