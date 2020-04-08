package com.example.uds.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uds.R
import com.example.uds.model.Schedule
import kotlinx.android.synthetic.main.adapter_schedule.view.*

class SchedulesAdapter(
    private val schedulesList: List<Schedule>,
    private val onClick: (String, Boolean) -> Unit
) :
    RecyclerView.Adapter<SchedulesAdapter.ViewHolder>() {
    override fun getItemCount(): Int = schedulesList.count()
    private var selectedItem: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_schedule, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(schedulesList[position], onClick)
            collapsible.visibility = if (selectedItem == position) View.VISIBLE else View.GONE

            root.setOnClickListener {
                selectedItem = if (selectedItem == position) -1 else position
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.schedule_title
        private val intro: TextView = itemView.schedule_intro
        private val description: TextView = itemView.schedule_description
        private val button: Button = itemView.button
        val collapsible: LinearLayout = itemView.collapsibleView
        val root: LinearLayout = itemView.root

        fun bind(Schedule: Schedule, onClick: (String, Boolean) -> Unit) {
            title.text = Html.fromHtml("<b>Title:</b> ${Schedule.title}")
            intro.text = Html.fromHtml("<b>Introdução:</b> ${Schedule.intro}")
            description.text = Html.fromHtml("<b>Description:</b> ${Schedule.description}")
            button.text = if (Schedule.isDone!!) "Reabrir" else "Finalizar"

            button.setOnClickListener {
                onClick(Schedule.id!!, Schedule.isDone!!)
            }
        }
    }
}