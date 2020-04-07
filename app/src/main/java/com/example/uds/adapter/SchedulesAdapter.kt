package com.example.uds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uds.R
import com.example.uds.model.Schedule
import kotlinx.android.synthetic.main.adapter_schedule.view.*

class SchedulesAdapter(private val schedulesList: List<Schedule>): RecyclerView.Adapter<SchedulesAdapter.ViewHolder>() {
    override fun getItemCount(): Int = schedulesList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_schedule, parent, false)
       return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedulesList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.schedule_title
        private val intro: TextView = itemView.schedule_intro

        fun bind(Schedule: Schedule) {
            title.text = Schedule.title
            intro.text = Schedule.intro
        }
    }
}