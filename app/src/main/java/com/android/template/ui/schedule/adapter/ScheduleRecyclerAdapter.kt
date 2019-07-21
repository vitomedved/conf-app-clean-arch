package com.android.template.ui.schedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.android.template.R
import template.android.com.domain.model.EventInfo

class ScheduleRecyclerAdapter (private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>() {

    private val eventInfos: MutableList<EventInfo> = mutableListOf()

    fun setItems(eventInfoList: List<EventInfo>) {
        eventInfos.clear()
        eventInfos.addAll(eventInfoList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = eventInfos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(layoutInflater.inflate(R.layout.list_item_event, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    private fun getItem(index: Int): EventInfo = eventInfos[index]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.list_item_event_starting_time)
        lateinit var startingTime: TextView

        @BindView(R.id.list_item_event_name)
        lateinit var eventName: TextView

        @BindView(R.id.list_item_event_image_view_event_type)
        lateinit var eventTypeImageView: ImageView

        @BindView(R.id.list_item_event_time_from_to)
        lateinit var timeFromTo: TextView

        @BindView(R.id.list_item_event_location)
        lateinit var location: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun render(eventInfo: EventInfo) {
            // TODO: time utils to parse time - check if exists, if not, create,
            // TODO: when events are received sort them by date/time, show only current date events on recycler
            // TODO: when loading, hide everything (header layout), show after loading is done
            startingTime.text = "09:00"
            eventName.text = eventInfo.name
            //TODO: evt type image view
            timeFromTo.text = "09:00 - 12:00"
            location.text = eventInfo.hall
        }
    }
}