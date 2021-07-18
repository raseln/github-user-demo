package com.rasel.githubusers.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rasel.githubusers.data.api.GithubApiService
import com.rasel.githubusers.data.models.UserDetails
import com.rasel.githubusers.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class GithubRepositoryTest {

    @Mock
    lateinit var apiService: GithubApiService

    private lateinit var sut: GithubRepository

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GithubRepository(apiService)
    }

    @Test
    fun when_search_api_call_is_successful() = runBlockingTest {
        // Arrange
        val userDetails = UserDetails(1, "Name", "Bio",
            "https://avatars.githubusercontent.com/u/1?v=4",
            "test", "", "", "",
            "", "", false, "",
        "", "User", "", "", "", "",
        "", 100, 500, "", "", 253,
            263, 36, 37, 37, "",
            "2007-10-20T05:24:19Z", "2007-10-20T05:24:19Z")
        Mockito.`when`(
            apiService.getUserDetails(
                Mockito.anyString()
            )
        )
            .thenReturn(Response.success(userDetails))

        // Act
        sut.getUserDetails("test")

        // Assert
        assertEquals(sut.userDetailsLiveData.getOrAwaitValue(), userDetails)
        assertEquals(sut.errorLiveData.getOrAwaitValue(), null)
        Mockito.verify(apiService, Mockito.times(1)).getUserDetails(
            Mockito.anyString()
        )
        Mockito.verifyNoMoreInteractions(apiService)
    }

    @Test
    fun when_search_api_call_is_not_successful() = runBlockingTest {
        // Arrange
        val errorBody = ByteArray(0).toResponseBody(null)
        Mockito.`when`(
            apiService.getUserDetails(
                Mockito.anyString()
            )
        )
            .thenReturn(Response.error(400, errorBody))

        // Act
        sut.getUserDetails("test")

        // Assert
        assertEquals(sut.userDetailsLiveData.getOrAwaitValue(), null)
        assertEquals(sut.errorLiveData.getOrAwaitValue(), errorBody.toString())
        Mockito.verify(apiService, Mockito.times(1)).getUserDetails(
            Mockito.anyString()
        )
        Mockito.verifyNoMoreInteractions(apiService)
    }

    @Test
    fun when_search_api_call_fails_with_exception() = runBlockingTest {
        // Arrange
        Mockito.`when`(
            apiService.getUserDetails(
                Mockito.anyString()
            )
        )
            .thenThrow(RuntimeException("Invalid operation"))

        // Act
        sut.getUserDetails("test")

        //Assert
        assertEquals(sut.userDetailsLiveData.getOrAwaitValue(), null)
        assertEquals(sut.errorLiveData.getOrAwaitValue(), "Invalid operation")
        Mockito.verify(apiService, Mockito.times(1)).getUserDetails(
            Mockito.anyString()
        )
        Mockito.verifyNoMoreInteractions(apiService)
    }
}