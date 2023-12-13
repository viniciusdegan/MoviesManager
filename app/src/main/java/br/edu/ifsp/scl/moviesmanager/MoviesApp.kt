package br.edu.ifsp.scl.moviesmanager

import android.app.Application
import br.edu.ifsp.scl.moviesmanager.framework.di.MoviesDependencies.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            allowOverride(true)
            androidLogger()
            androidContext(this@MoviesApp)
            modules(listOf(moviesModule))
        }
    }
}