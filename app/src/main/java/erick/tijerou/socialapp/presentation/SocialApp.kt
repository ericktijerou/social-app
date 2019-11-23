package erick.tijerou.socialapp.presentation

import android.app.Application
import erick.tijerou.socialapp.BuildConfig.DEBUG
import erick.tijerou.socialapp.data.network.di.networkModule
import erick.tijerou.socialapp.presentation.config.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

open class SocialApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(DEBUG) Timber.plant(Timber.DebugTree())
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SocialApp)
            modules(koinModules())
        }
    }

    open fun koinModules() = listOf(
        networkModule,
        cacheModule,
        repositoryModule,
        interactorModule,
        viewModelModule
    )

}
