package com.example.myapplication.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.*
import com.example.myapplication.detail.di.DetailModule
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(),
    DetailPresenter.View {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private val component by lazy { app.component.plus(DetailModule(this)) }
    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        component.inject(this)

        presenter.onCreate(intent.getLongExtra(EXTRA_ID, -1))
    }

    override fun setTitle(title: String) = run { supportActionBar?.title = title }

    override fun setImage(url: String) = detail_thumb.loadUrl(url)

    override fun setDetailIndicatorVisible(visible: Boolean) = with(detail_video_indicator) {
        if (visible) {
            show()
        } else {
            hide()
        }
    }

}
