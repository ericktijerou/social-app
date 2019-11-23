package erick.tijerou.socialapp.presentation.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.presentation.ui.CoroutinesViewModel

class UserDetailViewModel(
    private val interactor: UserInteractor
) : CoroutinesViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _user = MutableLiveData<User>()
    private val _error = MutableLiveData<Throwable?>()

    val loading: LiveData<Boolean> get() = _loading
    val user: LiveData<User> get() = _user
    val error: LiveData<Throwable?> get() = _error

    fun getUser(userId: Long) {
        launch {
            try {
                _loading.value = true
                _user.value = interactor.getUser(userId)
                _error.value = null
            } catch (exception: Exception) {
                _error.value = exception
            } finally {
                _loading.value = false
            }
        }
    }

}