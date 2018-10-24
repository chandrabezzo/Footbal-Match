package com.bezzo.football2

import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.football2.features.nextEvent.NextEventContracts
import com.bezzo.football2.features.nextEvent.NextEventPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextEventPresenterTest {

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var view : NextEventContracts.View

    private val leagueId = "4328"

    private lateinit var presenter : NextEventPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = NextEventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getNextEvent(){
        val events = mutableListOf<Event>()
        val response = EventResponse(events)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(SportsRepo.getNextEvent(leagueId)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEvents(leagueId)

        Mockito.verify(view).showProgressBar()
        Mockito.verify(view).showEvents(events)
        Mockito.verify(view).hideProgressBar()
    }
}