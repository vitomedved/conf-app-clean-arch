package com.android.template.ui.schedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.android.template.R
import template.android.com.domain.model.Event

class ScheduleRecyclerAdapter (private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>() {

    private val events: MutableList<Event> = mutableListOf()

    fun setItems(eventList: List<Event>) {
        events.clear()
        events.addAll(eventList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(layoutInflater.inflate(R.layout.list_item_event, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    private fun getItem(index: Int): Event = events[index]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.list_item_event_name)
        lateinit var eventName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun render(event: Event) {
            eventName.text = event.name
        }
    }
}