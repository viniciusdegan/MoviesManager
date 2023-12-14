package br.edu.ifsp.scl.moviesmanager.framework.di

import androidx.room.Room
import br.edu.ifsp.scl.moviesmanager.framework.datasource.MoviesDatabase
import br.edu.ifsp.scl.moviesmanager.data.datasource.MovieDao
import br.edu.ifsp.scl.moviesmanager.data.repository.MovieRepository
import br.edu.ifsp.scl.moviesmanager.domain.usecase.MovieUseCase
import br.edu.ifsp.scl.moviesmanager.presentation.component.MovieAdapter
import br.edu.ifsp.scl.moviesmanager.presentation.component.Separator
import br.edu.ifsp.scl.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object MoviesDependencies {
    val moviesModule = module {
        single {
            Room.databaseBuilder(
                androidContext(), MoviesDatabase::class.java, MoviesDatabase.DATABASE_NAME
            ).build()
        }
        single<MovieDao> {
            get<MoviesDatabase>().favoriteLinkDAO()
        }
        viewModelOf(::MovieViewModel)
        factoryOf(::Separator)
        factoryOf(::MovieAdapter)
        factoryOf(::MovieRepository) { bind<MovieUseCase>() }
    }
}