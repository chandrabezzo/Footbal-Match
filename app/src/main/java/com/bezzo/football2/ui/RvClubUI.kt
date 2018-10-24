package com.bezzo.football2.ui

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import com.bezzo.football2.R
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class RvClubUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(6)
            }
            backgroundColor = Color.WHITE

            textView {
                id = R.id.event_date
                textSize = 16f
                text = "Selasa, 28 November 1995"
                gravity = Gravity.CENTER_HORIZONTAL
                textColor = ctx.getColor(R.color.colorPrimary)
            }.lparams {
                width = matchParent
                height = wrapContent
                topMargin = dip(8)
            }

            linearLayout {
                lparams {
                    width = matchParent
                    height = wrapContent
                    bottomMargin = dip(8)
                }
                gravity = Gravity.CENTER_HORIZONTAL

                textView {
                    id = R.id.home_team
                    text = "Persib"
                }.lparams {
                    marginEnd = dip(16)
                }

                textView {
                    id = R.id.home_score
                    textSize = 21f
                    text = "2"
                    textColor = Color.BLACK
                }

                textView {
                    text = ctx.getString(R.string.versus)
                    textSize = 15f
                }.lparams {
                    marginStart = dip(6)
                    marginEnd = dip(6)
                }

                textView {
                    id = R.id.away_score
                    textSize = 21f
                    text = "2"
                    textColor = Color.BLACK
                }

                textView {
                    id = R.id.away_team
                    text = "Persija"
                }.lparams {
                    marginStart = dip(16)
                }
            }
        }
    }
}