package br.edu.ifsp.scl.moviesmanager.data.repository

import br.edu.ifsp.scl.moviesmanager.data.datasource.MovieDao
import br.edu.ifsp.scl.moviesmanager.data.model.MovieModel
import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie
import br.edu.ifsp.scl.moviesmanager.domain.usecase.MovieUseCase

class MovieRepository(private val movieDao: MovieDao) : MovieUseCase {
    override suspend fun createMovie(movie: Movie) {
        movieDao.createMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun updateMovie(movie: Movie) {
        movieDao.updateMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies().map { it.toEntity() }
    }
}