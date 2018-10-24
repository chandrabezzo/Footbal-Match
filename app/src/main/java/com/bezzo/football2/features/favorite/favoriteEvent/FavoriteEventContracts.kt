package com.bezzo.football2.features.favorite.favoriteEvent

class FavoriteEventContracts {

    interface View {
        fun showEvents()

        fun showProgressBar()

        fun hideProgressBar()
    }
}