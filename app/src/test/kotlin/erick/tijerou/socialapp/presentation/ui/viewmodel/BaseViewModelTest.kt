package erick.tijerou.socialapp.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import erick.tijerou.socialapp.presentation.ui.viewmodel.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

open class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @ExperimentalCoroutinesApi
    protected inline fun runBlockingTest(crossinline block: () -> Unit) =
        coroutinesTestRule.testDispatcher.runBlockingTest { block() }

}