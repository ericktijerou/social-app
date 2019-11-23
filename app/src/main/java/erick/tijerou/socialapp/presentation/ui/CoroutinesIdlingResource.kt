package erick.tijerou.socialapp.presentation.ui

import androidx.test.espresso.idling.CountingIdlingResource

object CoroutinesIdlingResource {

    private val idlingResourceName = "COROUTINES_IDLING_RESOURCE"

    val idlingResource = CountingIdlingResource(idlingResourceName)

}