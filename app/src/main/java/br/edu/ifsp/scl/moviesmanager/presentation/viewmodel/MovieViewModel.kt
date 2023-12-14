package br.edu.ifsp.scl.moviesmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie
import br.edu.ifsp.scl.moviesmanager.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _listOfMovies = MutableLiveData<List<Movie>>()
    val listOfMovies: LiveData<List<Movie>> = _listOfMovies
    fun getAllMovies() {
        viewModelScope.launch(dispatcher) {
            _listOfMovies.postValue(movieUseCase.getAllMovies())
        }
    }

    fun createMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.createMovie(movie)
            getAllMovies()
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie)
            getAllMovies()
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.deleteMovie(movie)
            getAllMovies()
        }
    }

    fun ratingMovie(movie: Movie, rating: Int) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie.copy(rate = rating))
            getAllMovies()
        }
    }

    fun watchMovie(movie: Movie, hasWatched: Boolean) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie.copy(ifWatched = hasWatched))
            getAllMovies()
        }
    }
}