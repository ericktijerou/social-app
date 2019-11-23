package erick.tijerou.socialapp.domain

import erick.tijerou.socialapp.domain.exception.ServerException
import erick.tijerou.socialapp.domain.interactor.implementation.UserInteractorImp
import erick.tijerou.socialapp.domain.repository.UserRepository
import erick.tijerou.socialapp.mock.UserMock.oneUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UserInteractorTest {

    private val repository = mockk<UserRepository>()
    private val interactor = UserInteractorImp(repository)

    @Test
    fun `should return an empty list of users`() = runBlocking {
        coEvery { repository.getUsers(false) } returns emptyList()
        val result = interactor.getUsers(false)
        coVerify { repository.getUsers(false) }
        Assert.assertEquals(0, result.size)
    }

    @Test
    fun `should return a list containing users`() = runBlocking {
        val userList = arrayListOf(oneUser)
        coEvery { repository.getUsers(false) } returns userList
        val result = interactor.getUsers(false)
        coVerify { repository.getUsers(false) }
        Assert.assertEquals(userList, result)
    }

    @Test
    fun `should find a user by id`() = runBlocking {
        val userMock = oneUser
        coEvery { repository.getUser(any()) } returns userMock
        val result = interactor.getUser(1)
        coVerify { repository.getUser(any()) }
        Assert.assertEquals(userMock, result)
    }

    @Test(expected = ServerException::class)
    fun `shouldn't find a user by id`() = runBlocking {
        coEvery { repository.getUser(any()) } throws ServerException()
        interactor.getUser(1)
        coVerify { repository.getUser(any()) }
    }

}