package com.bezzo.football2

import com.bezzo.core.data.model.Team
import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.football2.features.match.detail.DetailContracts
import com.bezzo.football2.features.match.detail.DetailPresenter
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var view : DetailContracts.View

    private val idHome = "134778"
    private val homeTeam = "Southampton"

    private val idAway = "133619"
    private val awayTeam = "Brighton"

    private lateinit var presenter : DetailPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getHomeBadge(){
        val teams = mutableListOf<Team>()
        val response = TeamResponse(teams)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(SportsRepo.getTeam(homeTeam)),
                TeamResponse::class.java)).thenReturn(response)

        presenter.getHome(idHome, homeTeam)

        for (team in teams){
            if (team.idTeam == idHome){
                Mockito.verify(view).homeTeam(team)
                break
            }
        }
    }

    @Test
    fun getAwayBadge(){
        val teams = ArrayList<Team>()
        val response = TeamResponse(teams)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(SportsRepo.getTeam(awayTeam)),
                TeamResponse::class.java)).thenReturn(response)

        presenter.getAway(idAway, awayTeam)

        for (team in teams){
            if (team.idTeam == idAway){
                assertEquals(team.idTeam, idHome)
                assertEquals(team.strTeam, homeTeam)
                Mockito.verify(view).awayTeam(team)
                break
            }
        }
    }
}