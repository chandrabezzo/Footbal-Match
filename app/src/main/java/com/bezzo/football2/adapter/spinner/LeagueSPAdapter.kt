package com.bezzo.football2.adapter.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.football2.R
import kotlinx.android.synthetic.main.item_sp_league_next.view.*

class LeagueSPAdapter constructor(var list : ArrayList<LeagueResponse.League>)
    : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var layoutView = LayoutInflater.from(parent?.context).inflate(R.layout.item_sp_league_next,
                null)
        layoutView.tv_league.text = list[position].strLeague
        return layoutView
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    fun update(values : List<LeagueResponse.League>){
        list.clear()
        list.addAll(values)
        notifyDataSetChanged()
    }
}