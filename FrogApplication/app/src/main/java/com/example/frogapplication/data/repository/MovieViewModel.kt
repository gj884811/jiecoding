package com.example.frogapplication.data.repository

import androidx.lifecycle.*
import com.example.frogapplication.data.MovieListResponse
import com.example.frogapplication.until.Coroutines
import kotlinx.coroutines.Job
import repository.MovieRepository

class MovieViewModel(
  private val repository: MovieRepository,
) :  ViewModel() {
  var email: String? = null
  var password: String? = null


  lateinit var job: Job
  private val _movielist = MutableLiveData<MovieListResponse>()
  val movieList: LiveData<MovieListResponse>
    get() = _movielist


  public override fun onCleared() {
    super.onCleared()
    if(::job.isInitialized) job.cancel()
  }

  fun getMovieList(apiKey: String,title: String, year: String) {
      job = Coroutines.ioThenMain(
        {repository.getMovieList(apiKey,title,year)},
        {_movielist.value = it }
      )
    }




}