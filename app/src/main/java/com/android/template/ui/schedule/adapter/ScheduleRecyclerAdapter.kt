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
import com.android.template.utils.calendar.CalendarUtils
import template.android.com.domain.model.EventInfo

class ScheduleRecyclerAdapter(private val layoutInflater: LayoutInflater, private val calendarUtils: CalendarUtils) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>() {

    private val eventInfoList: MutableList<EventInfo> = mutableListOf()

    fun setItems(eventInfoList: List<EventInfo>) {
        this.eventInfoList.clear()
        this.eventInfoList.addAll(eventInfoList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = eventInfoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(layoutInflater.inflate(R.layout.list_item_event, parent, false), calendarUtils)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    private fun getItem(index: Int): EventInfo = eventInfoList[index]

    class ViewHolder(itemView: View, private val calendarUtils: CalendarUtils) : RecyclerView.ViewHolder(itemView) {

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
            startingTime.text = eventInfo.startingTime
            eventName.text = eventInfo.name
            //TODO: evt type image view
            timeFromTo.text = calendarUtils.getTimeDurationString(eventInfo.startingTime, eventInfo.durationInMinutes)
            location.text = eventInfo.hall
        }
    }
}