package erick.tijerou.socialapp.data

import erick.tijerou.socialapp.data.cache.UserDataStore
import erick.tijerou.socialapp.data.network.UserCloudStore
import erick.tijerou.socialapp.data.repository.UserRepositoryImp
import erick.tijerou.socialapp.domain.exception.ServerException
import erick.tijerou.socialapp.mock.UserMock.oneUserModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UserRepositoryTest {

    private val dataStore = mockk<UserDataStore>()
    private val cloudStore = mockk<UserCloudStore>()
    private val userRepository = UserRepositoryImp(cloudStore, dataStore)

    @Test
    fun `should return a list containing users`() = runBlocking {
        coEvery { dataStore.getUsers() } returns arrayListOf(oneUserModel)
        val result = userRepository.getUsers(false)
        coVerify { dataStore.getUsers() }
        Assert.assertEquals(1, result.size)
    }


    @Test(expected = ServerException::class)
    fun `should throw an exception when get a user by id`() = runBlocking {
        coEvery { dataStore.getUser(any()) } throws ServerException()

        userRepository.getUser(1)

        coVerify { dataStore.getUser(any()) }
    }

}