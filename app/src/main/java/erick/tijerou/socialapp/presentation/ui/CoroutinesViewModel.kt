package erick.tijerou.socialapp.presentation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class CoroutinesViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModeScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        CoroutinesIdlingResource.idlingResource.increment().let {
            viewModeScope.launch(block = block).also {
                it.invokeOnCompletion { CoroutinesIdlingResource.idlingResource.decrement() }
            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
