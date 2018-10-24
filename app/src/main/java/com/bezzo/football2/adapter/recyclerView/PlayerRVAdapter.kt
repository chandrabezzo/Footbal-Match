package com.bezzo.football2.adapter.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.data.model.PlayerResponse
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import com.bezzo.football2.R
import kotlinx.android.synthetic.main.item_rv_player.view.*

class PlayerRVAdapter(private val context: Context, private val list : ArrayList<PlayerResponse.Player>)
    : RecyclerView.Adapter<PlayerRVAdapter.Item>() {

    lateinit var listener : OnItemClickListener

    fun setOnItemClick(listener : OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_player,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    inner class Item(itemView : View) : BaseHolder<PlayerResponse.Player>(itemView) {

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: PlayerResponse.Player) {
            GlideApp.with(context).load(model.strCutout).into(itemView.iv_player)
            itemView.tv_player_name.text = model.strPlayer
            itemView.tv_position.text = model.strPosition
        }
    }
}