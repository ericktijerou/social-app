package erick.tijerou.socialapp.data.di

import erick.tijerou.socialapp.data.cache.di.cacheModule
import erick.tijerou.socialapp.data.network.di.networkModule
import erick.tijerou.socialapp.data.repository.di.repositoryModule
import erick.tijerou.socialapp.core.util.listByElementsOf
import org.koin.core.module.Module

internal val dataModules by lazy {
    listByElementsOf<Module>(
        cacheModule,
        networkModule,
        repositoriesModules
    )
}

internal val repositoriesModules by lazy {
    listByElementsOf<Module>(
        repositoryModule
    )
}
