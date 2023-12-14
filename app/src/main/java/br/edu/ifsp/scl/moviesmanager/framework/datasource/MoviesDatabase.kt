package br.edu.ifsp.scl.moviesmanager.framework.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.moviesmanager.data.datasource.MovieDao
import br.edu.ifsp.scl.moviesmanager.data.model.MovieModel

@Database(entities = [MovieModel::class], version = 5)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun favoriteLinkDAO(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_manager"
    }
}