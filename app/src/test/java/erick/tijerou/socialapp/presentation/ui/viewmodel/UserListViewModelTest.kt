package erick.tijerou.socialapp.presentation.ui.viewmodel

import androidx.lifecycle.Observer
import erick.tijerou.socialapp.domain.entity.User
import erick.tijerou.socialapp.domain.interactor.UserInteractor
import erick.tijerou.socialapp.mock.UserMock.oneUser
import erick.tijerou.socialapp.presentation.ui.userlist.UserListViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

class UserListViewModelTest : BaseViewModelTest() {

    private val interactor = mockk<UserInteractor>()

    private val loadingObserver = mockk<Observer<Boolean>>()

    private val usersObserver = mockk<Observer<List<User>>>()

    private val errorObserver = mockk<Observer<Throwable>>()

    private val viewModel = UserListViewModel(interactor = interactor)

    @ExperimentalCoroutinesApi
    @Test
    fun `no interactions`() = runBlockingTest {
        every { usersObserver.onChanged(any()) } just Runs

        viewModel.users.observeForever(usersObserver)

        coVerify(exactly = 0) { interactor.getUsers(false) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get users with success`() = runBlockingTest {
        coEvery { interactor.getUsers(false) } returns arrayListOf(oneUser)

        every { loadingObserver.onChanged(any()) } just Runs

        every { usersObserver.onChanged(any()) } just Runs

        viewModel.loading.observeForever(loadingObserver)

        viewModel.users.observeForever(usersObserver)

        viewModel.getUsers(false)

        coVerify { interactor.getUsers(false) }

        verify(exactly = 2) { loadingObserver.onChanged(any()) }

        verify { usersObserver.onChanged(any()) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `throws exception when get users`() = runBlockingTest {
        coEvery { interactor.getUsers(false) } throws Exception()

        every { loadingObserver.onChanged(any()) } just Runs

        every { usersObserver.onChanged(any()) } just Runs

        every { errorObserver.onChanged(any()) } just Runs

        viewModel.loading.observeForever(loadingObserver)

        viewModel.users.observeForever(usersObserver)

        viewModel.error.observeForever(errorObserver)

        viewModel.getUsers(false)

        coVerify { interactor.getUsers(false) }

        verify(exactly = 2) { loadingObserver.onChanged(any()) }

        verify(exactly = 0) { usersObserver.onChanged(any()) }

        verify { errorObserver.onChanged(any()) }
    }

}