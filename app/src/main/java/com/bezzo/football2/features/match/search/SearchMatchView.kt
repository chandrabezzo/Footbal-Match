package com.bezzo.football2.features.match.search

import com.bezzo.core.data.model.Event

interface SearchMatchView {
    fun showMatch(searchedMatch : List<Event>)

    fun showLoadMore()

    fun hideLoadMore()
}