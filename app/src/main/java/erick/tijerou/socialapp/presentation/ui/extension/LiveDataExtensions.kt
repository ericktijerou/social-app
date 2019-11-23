package erick.tijerou.socialapp.presentation.ui.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, body: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer { body(it) })
}