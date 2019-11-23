package erick.tijerou.socialapp.domain.di

import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.domain.interactor.implementation.UserInteractorImp
import org.koin.dsl.module

internal val domainModules = module {
    single<UserInteractor> { UserInteractorImp(get()) }
}