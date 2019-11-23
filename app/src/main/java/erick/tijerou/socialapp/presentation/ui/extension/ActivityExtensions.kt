package erick.tijerou.socialapp.presentation.ui.extension

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import kotlin.reflect.KClass

fun <T> Activity.startNewActivity(
    target: KClass<T>,
    sharedViews: Array<Pair<View, String>>,
    extras: Intent.() -> Unit = {  }
) where T : Activity {
    val intent = Intent(this, target.java)
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedViews).toBundle()
    intent.apply { extras() }
    this.startActivity(intent, options)
}