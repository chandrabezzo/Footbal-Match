package com.bezzo.football2.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.bezzo.core.R.color.colorPrimary
import com.bezzo.football2.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventUI<T> : AnkoComponent<T> {

    @SuppressLint("ResourceAsColor")
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefreshLayout {
                id = R.id.swipe_event

                setColorSchemeColors(colorPrimary,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView {
                        id = R.id.rv_event
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar {
                        id = R.id.pb_event
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}