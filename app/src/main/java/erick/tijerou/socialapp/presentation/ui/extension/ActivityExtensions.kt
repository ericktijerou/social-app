package erick.tijerou.socialapp.presentation.ui.extension

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass

fun <T> Activity.startNewActivity(
    target: KClass<T>,
    extras: Intent.() -> Unit = {  }
) where T : Activity {
    val intent = Intent(this, target.java)
    intent.apply { extras() }
    this.startActivity(intent)
}