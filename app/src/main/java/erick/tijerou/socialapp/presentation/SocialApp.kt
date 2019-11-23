package erick.tijerou.socialapp.presentation

import android.app.Application
import erick.tijerou.socialapp.BuildConfig.DEBUG
import erick.tijerou.socialapp.core.util.listByElementsOf
import erick.tijerou.socialapp.data.di.dataModules
import erick.tijerou.socialapp.domain.di.domainModules
import erick.tijerou.socialapp.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
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
            modules(modules)
        }
    }


    private val modules by lazy {
        listByElementsOf<Module>(
            dataModules,
            domainModules,
            presentationModules
        )
    }

}
