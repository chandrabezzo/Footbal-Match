package com.bezzo.football2.adapter.recyclerView

import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.data.model.Event
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.DateTimeUtils
import com.bezzo.football2.R
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import kotlinx.android.synthetic.main.item_rv_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class EventRVAdapter(private val list : ArrayList<Event>, private val isNext : Boolean)
    : RecyclerView.Adapter<EventRVAdapter.Item>() {

    lateinit var listener : OnItemClickListener

    fun setOnItemClick(listener : OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_event,
                parent, false))
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    override fun getItemCount() = list.size

    inner class Item(itemView : View) : BaseHolder<Event>(itemView) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(it, layoutPosition)
            }

            if (isNext){
                itemView.iv_notification.show()
            }
            else {
                itemView.iv_notification.hide()
            }
        }

        override fun setContent(model: Event) {
            val dateInEpoch = model.dateEvent?.let { DateTimeUtils.dateToEpoch(it) }
            itemView.tv_date.text = dateInEpoch?.let { DateTimeUtils.epochToHumanDate(it) }
            model.eventTime?.let {
                val timeSplit = it.split("+")
                itemView.tv_time.text = timeSplit[0]
            }
            itemView.tv_home.text = model.homeTeam
            itemView.tv_home_score.text = model.homeScore
            itemView.tv_away.text = model.awayTeam
            itemView.tv_away_score.text = model.awayScore

            var time = model.eventTime?.replace(" GMT", "")?.split("+")
            var splitTime = time?.get(0)?.split(":")
            var splitDate = model.dateEvent?.split("-")

            val cal = Calendar.getInstance()
            splitDate?.get(0)?.toInt()?.let { cal.set(Calendar.YEAR, it) }
            splitDate?.get(1)?.toInt()?.let { cal.set(Calendar.MONTH, it - 1) }
            splitDate?.get(2)?.toInt()?.let { cal.set(Calendar.DAY_OF_MONTH, it) }
            splitTime?.get(0)?.toInt()?.let { cal.set(Calendar.HOUR_OF_DAY, it) }
            splitTime?.get(1)?.toInt()?.let { cal.set(Calendar.MINUTE, it) }

            itemView.iv_notification.setOnClickListener {
                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.timeInMillis)
                intent.putExtra(CalendarContract.Events.TITLE, "${model.homeTeam} vs ${model.awayTeam}")
                itemView.context.startActivity(intent)
            }
        }
    }
}