package com.felix.ticketin.data

import com.felix.ticketin.data.room.DbHelper
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.data.service.ApiHelper
import com.felix.ticketin.data.service.ApiService
import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class RepositoryTest {
    //collaborator
    private lateinit var apiService: ApiService
    private lateinit var apiHelper: ApiHelper
    private lateinit var dbHelper: DbHelper

    //sut
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        apiService = mockk()
        dbHelper = mockk()
        apiHelper = ApiHelper(apiService)
        repository = Repository(dbHelper, apiHelper)
    }

    @Test
    fun getAllFav(): Unit = runBlocking {
        //given
        val fav = mockk<List<FavEntity>>()
        every {
            runBlocking {
                dbHelper.getAllFav()
            }
        }returns fav

        //when
        repository.getAllFav()

        //then
        verify {
            runBlocking {
                runBlocking { dbHelper.getAllFav() }
            }
        }
    }

    @Test
    fun getFavorite(): Unit = runBlocking {
        //given
        val getFav = mockk<FavEntity>()
        every {
            runBlocking {
                dbHelper.getFavorite(id = 0)
            }
        }returns getFav

        //when
        repository.getFavorite(id = 0)

        //then
        verify {
            runBlocking {
                runBlocking { dbHelper.getFavorite(id = 0) }
            }
        }
    }

    @Test
    fun insert(): Unit = runBlocking {
        //given
        val insert = mockk<Unit>()
        every {
            runBlocking {
                dbHelper.insert(entity = FavEntity(id = 0, image = "default", name = "default"))
            }
        }returns insert

        //when
        repository.insert(entity = FavEntity(id = 0, image = "default", name = "default"))

        //then
        verify {
            runBlocking {
                runBlocking { dbHelper.insert(entity = FavEntity(id = 0, image = "default", name = "default")) }
            }
        }
    }

    @Test
    fun delete(): Unit = runBlocking {
        //given
        val delete = mockk<Unit>()
        every {
            runBlocking {
                dbHelper.deleteFavorite(entity = FavEntity(id = 0, image = "default", name = "default"))
            }
        }returns delete

        //when
        repository.delete(entity = FavEntity(id = 0, image = "default", name = "default"))

        //then
        verify {
            runBlocking {
                runBlocking { dbHelper.deleteFavorite(entity = FavEntity(id = 0, image = "default", name = "default")) }
            }
        }
    }

    @Test
    fun getAllPlayingNow(): Unit = runBlocking {
        //Given
        val responseMovies = mockk<GetAllPlayingNowIResponse>()

        every {
            runBlocking {
                apiService.getAllPlayingNow()
            }
        }returns responseMovies

        //When
        repository.getAllPlayingNow()

        //Then
        verify {
            runBlocking {
                runBlocking { apiService.getAllPlayingNow() }
            }
        }
    }

    @Test
    fun getAllComingSoon(): Unit = runBlocking {
        //Given
        val responseComingSoonMovies = mockk<GetAllComingSoonResponse>()

        every {
            runBlocking {
                apiService.getAllComingSoon()
            }
        }returns responseComingSoonMovies

        //When
        repository.getAllComingSoon()

        //Then
        verify {
            runBlocking {
                runBlocking { apiService.getAllComingSoon() }
            }
        }
    }
}