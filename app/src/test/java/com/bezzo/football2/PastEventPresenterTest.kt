package com.bezzo.football2

import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.football2.features.match.pastEvent.PastEventPresenter
import com.bezzo.football2.features.match.pastEvent.PastEventView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PastEventPresenterTest {

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var view : PastEventView

    private val leagueId = "4328"

    private lateinit var presenter : PastEventPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = PastEventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPastEvent(){
        val events = mutableListOf<Event>()
        val response = EventResponse(events)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(SportsRepo.getPastEvent(leagueId)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEvents(leagueId)

        Mockito.verify(view).showProgressBar()
        Mockito.verify(view).showEvents(events)
        Mockito.verify(view).hideProgressBar()
    }
}