package com.bezzo.football2.features.teamDetail.players

import com.bezzo.core.base.BaseFragmentView
import com.bezzo.core.data.model.PlayerResponse

interface PlayersView : BaseFragmentView {

    fun showLoadMore()

    fun hideLoadMore()

    fun showPlayers(values : List<PlayerResponse.Player>)
}