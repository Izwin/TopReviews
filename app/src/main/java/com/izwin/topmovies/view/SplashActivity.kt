package com.izwin.topmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izwin.topmovies.R
import com.izwin.topmovies.api.ApiClient
import com.izwin.topmovies.model.MovieModel
import com.izwin.topmovies.utils.Constants
import com.izwin.topmovies.utils.Constants.API_KEY
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Main).launch { delay(2000); startActivity(Intent(this@SplashActivity , MainActivity::class.java).putExtra(Constants.MOVIE_EXTRA, ApiClient.create().getTopMovies(API_KEY, 0).results)) }
    }
}