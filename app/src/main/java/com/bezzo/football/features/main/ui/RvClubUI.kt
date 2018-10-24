package com.bezzo.football.features.main.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.bezzo.football.R
import org.jetbrains.anko.*

class RvClubUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView {
                id = R.id.club_image
                setImageResource(R.drawable.img_madrid)
            }.lparams(width = dip(50), height = dip(50))

            textView {
                id = R.id.club_name
                text = "Real Madrid"
            }.lparams {
                width = matchParent
                height = wrapContent
                margin = dip(10)
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }
}