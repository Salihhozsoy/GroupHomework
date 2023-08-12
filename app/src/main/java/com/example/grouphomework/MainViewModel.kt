package com.example.grouphomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _movieListState: MutableStateFlow<MovieListState> =
        MutableStateFlow(MovieListState.Idle)
    val movieListState: StateFlow<MovieListState> = _movieListState

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieListState.value = MovieListState.Loading
            delay(2000)
            _movieListState.value = MovieListState.Result(Database.movies)
        }
    }
}