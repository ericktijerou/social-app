package erick.tijerou.socialapp.presentation.di

import erick.tijerou.socialapp.presentation.ui.userdetail.UserDetailViewModel
import erick.tijerou.socialapp.presentation.ui.userlist.UserListViewModel
import erick.tijerou.socialapp.core.util.listByElementsOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


internal val presentationModules by lazy {
    listByElementsOf<Module>(
        viewModelModule
    )
}

internal val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
}