package com.bezzo.football.features.main.ui

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bezzo.core.R.color.colorPrimary
import com.bezzo.football.R
import com.bezzo.football.adapter.recyclerView.FootballClubRVAdapter
import com.bezzo.football.features.detail.DetailActivity
import com.bezzo.football.features.main.MainActivity
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainUI(val footbalAdapter : FootballClubRVAdapter) : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            recyclerView {
                id = R.id.rv_club
                val orientation = LinearLayoutManager.VERTICAL
                layoutManager = LinearLayoutManager(context, orientation, true)
                overScrollMode = View.OVER_SCROLL_NEVER
                adapter = footbalAdapter
            }
        }
    }
}