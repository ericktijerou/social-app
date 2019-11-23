package erick.tijerou.socialapp.data.repository.di

import erick.tijerou.socialapp.data.repository.UserRepositoryImp
import erick.tijerou.socialapp.domain.repository.UserRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get(), get()) }
}