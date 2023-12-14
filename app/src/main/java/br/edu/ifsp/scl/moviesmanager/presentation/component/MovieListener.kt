package br.edu.ifsp.scl.moviesmanager.presentation.component

import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie

interface MovieListener {
    fun onClick(movie: Movie)
    fun onDeleteClick(movie: Movie)
    fun onUpdateClick(movie: Movie)
    fun onRatingClick(movie: Movie)
    fun onWatchedClick(movie: Movie, hasWatched: Boolean)
}