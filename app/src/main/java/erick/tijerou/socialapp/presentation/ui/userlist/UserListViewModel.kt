package erick.tijerou.socialapp.presentation.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.presentation.ui.CoroutinesViewModel

class UserListViewModel(
    private val interactor: UserInteractor
) : CoroutinesViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _users = MutableLiveData<List<User>>()
    private val _error = MutableLiveData<Throwable>()

    val loading: LiveData<Boolean> get() = _loading
    val users: LiveData<List<User>> get() = _users
    val error: LiveData<Throwable> get() = _error

    init { getUsers() }

    fun getUsers() {
        launch {
            try {
                _loading.value = true
                _users.value = interactor.getUsers()
            } catch (exception: Exception) {
                _error.value = exception
            } finally {
                _loading.value = false
            }
        }
    }

}
