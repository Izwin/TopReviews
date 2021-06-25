package com.izwin.topmovies.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izwin.topmovies.api.ApiClient
import com.izwin.topmovies.model.MovieModel
import com.izwin.topmovies.utils.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel(){


    val popularMovieList : MutableLiveData<ArrayList<MovieModel>> = MutableLiveData()


    fun getPopular(offset: Int? = null) {
        val apiService = ApiClient.create()
        viewModelScope.launch(Dispatchers.IO) {
            val list = kotlin.runCatching { apiService.getTopMovies(API_KEY, offset ?: 0).results }
                .onSuccess { popularMovieList.postValue(it) }.onFailure { }
        }

    }
}