package com.bezzo.football2

import com.bezzo.core.data.network.ApiEndPoint
import com.bezzo.core.data.network.ApiRepository
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ApiRepositoryTest {

    @Test
    fun doRequest(){
        val apiRepository = mock(ApiRepository::class.java)
        val url = ApiEndPoint.GET_NEXT_EVENT_BY_LEAGUE_ID + "?id=4328"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}