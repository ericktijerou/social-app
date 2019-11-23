package erick.tijerou.socialapp.presentation.config.koin

import erick.tijerou.socialapp.data.repository.UserRepositoryImp
import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.domain.interactor.implementation.UserInteractorImp
import erick.tijerou.socialapp.domain.repository.UserRepository
import erick.tijerou.socialapp.presentation.ui.userdetail.UserDetailViewModel
import erick.tijerou.socialapp.presentation.ui.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get(), get()) }
}

val interactorModule = module {
    single<UserInteractor> { UserInteractorImp(get()) }
}

val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
}
