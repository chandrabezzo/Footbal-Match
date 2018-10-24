package com.bezzo.football.adapter.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bezzo.core.data.model.FootballClub
import com.bezzo.core.util.GlideApp
import com.bezzo.football.R
import com.bezzo.football.features.main.ui.RvClubUI
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FootballClubRVAdapter(private val context : Context, private val items : List<FootballClub>,
                            private val listener : (FootballClub) -> Unit)

    : RecyclerView.Adapter<FootballClubRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            RvClubUI().createView(AnkoContext.create(parent.context, parent))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        val imageClub = itemView.find<ImageView>(R.id.club_image)
        val nameClub = itemView.find<TextView>(R.id.club_name)

        fun bindItem(item : FootballClub, listener : (FootballClub) -> Unit){
            nameClub.text = item.name
            GlideApp.with(itemView.context).load(item.image).into(imageClub)

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}