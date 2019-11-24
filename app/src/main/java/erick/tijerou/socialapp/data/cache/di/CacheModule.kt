package erick.tijerou.socialapp.data.cache.di

import erick.tijerou.socialapp.data.cache.UserDataStore
import erick.tijerou.socialapp.data.cache.system.PreferencesHelper
import erick.tijerou.socialapp.data.cache.system.SystemDatabase
import org.koin.dsl.module

internal val cacheModule = module {
    single { SystemDatabase.newInstance(get()) }
    single { get<SystemDatabase>().userDao() }
    single { PreferencesHelper(get()) }
    single { UserDataStore(get(), get()) }
}