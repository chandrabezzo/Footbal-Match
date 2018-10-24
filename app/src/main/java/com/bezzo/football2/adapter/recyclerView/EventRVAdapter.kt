package com.bezzo.football2.adapter.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bezzo.core.data.model.Event
import com.bezzo.core.util.DateTimeUtils
import com.bezzo.football2.R
import com.bezzo.football2.ui.RvClubUI
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class EventRVAdapter(private val context : Context, private val items : List<Event>,
                     private val listener : (Event) -> Unit)

    : RecyclerView.Adapter<EventRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            RvClubUI().createView(AnkoContext.create(parent.context, parent))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        val eventDate = containerView.find<TextView>(R.id.event_date)
        val homeTeam = containerView.find<TextView>(R.id.home_team)
        val homeScore = containerView.find<TextView>(R.id.home_score)
        val awayTeam = containerView.find<TextView>(R.id.away_team)
        val awayScore = containerView.find<TextView>(R.id.away_score)

        fun bindItem(item : Event, listener : (Event) -> Unit){
            var dateInEpoch = DateTimeUtils.dateToEpoch(item.dateEvent)
            eventDate.text = DateTimeUtils.epochToHumanDate(dateInEpoch)
            homeTeam.text = item.homeTeam
            homeScore.text = item.homeScore
            awayTeam.text = item.awayTeam
            awayScore.text = item.awayScore

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}