package com.izwin.topmovies.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izwin.topmovies.R
import com.izwin.topmovies.adapters.MovieAdapter
import com.izwin.topmovies.model.MovieModel
import com.izwin.topmovies.utils.Constants
import com.izwin.topmovies.utils.Constants.MOVIE_EXTRA
import com.izwin.topmovies.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var offset = 0


        intent.extras!![Constants.MOVIE_EXTRA]?.let { viewmodel.popularMovieList.postValue(it as ArrayList<MovieModel>?); offset = (it as ArrayList<MovieModel>).size }


        val layoutManager = LinearLayoutManager(this)
        val adapter = MovieAdapter(this, arrayListOf<MovieModel>()){
            startActivity(Intent(Intent.ACTION_VIEW , Uri.parse(it.link.url)))
        }

        recycle_view.adapter = adapter
        recycle_view.layoutManager = layoutManager

        var loading = true
        recycle_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = layoutManager.itemCount

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        offset += 20
                        viewmodel.getPopular(offset)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        viewmodel.getPopular(offset)

        viewmodel.popularMovieList.observe(this){
            adapter.addMovies(it)
        }
    }
}