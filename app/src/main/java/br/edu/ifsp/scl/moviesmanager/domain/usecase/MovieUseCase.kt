package br.edu.ifsp.scl.moviesmanager.domain.usecase

import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie

interface MovieUseCase {
    suspend fun createMovie(movie: Movie)

    suspend fun updateMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun getAllMovies(): List<Movie>
}