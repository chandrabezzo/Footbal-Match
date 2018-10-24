package com.bezzo.football.features.detail

import android.view.Gravity
import android.view.View
import com.bezzo.football.R
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class DetailUI : AnkoComponent<DetailActivity> {
    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        verticalLayout {
            imageView {
                id = R.id.club_image
                setImageResource(R.drawable.img_madrid)
            }.lparams{
                width = dip(50)
                height = dip(50)
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(16)
            }

            textView {
                id = R.id.club_name
                text = "Real Madrid"
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams {
                width = matchParent
                height = wrapContent
                margin = dip(6)
            }

            textView {
                id = R.id.club_desc
                text = "Madridista"
            }.lparams {
                width = matchParent
                height = wrapContent
                margin = dip(16)
            }
        }
    }
}