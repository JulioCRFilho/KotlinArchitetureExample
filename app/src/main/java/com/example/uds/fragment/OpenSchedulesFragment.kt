package com.example.uds.fragment

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uds.R
import com.example.uds.adapter.SchedulesAdapter
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_done_schedules.*
import kotlinx.android.synthetic.main.fragment_open_schedules.*
import kotlinx.android.synthetic.main.fragment_open_schedules.emptyList
import kotlinx.android.synthetic.main.fragment_open_schedules.viewFlipper

class OpenSchedulesFragment(private val vm: HomeViewModel) :
    Fragment() {
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_open_schedules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = vm

        viewModel.dbStatusLiveData.observe(viewLifecycleOwner, Observer {
            viewFlipper.displayedChild = it.first ?: 0
        })

        viewModel.openSchedulesLiveData.observe(viewLifecycleOwner, Observer {
            if (it.count() > 0) {
                emptyList.visibility = View.GONE

                with(openRecyclerView) {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = SchedulesAdapter(it) { id, value ->
                        viewModel.updateSchedule(id, value)
                    }
                }
            } else {
                emptyList.visibility = View.VISIBLE
            }
        })
    }
}