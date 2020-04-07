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
import com.example.uds.adapter.OpenSchedulesAdapter
import com.example.uds.api.AuthInterface
import com.example.uds.utils.CustomDialog
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_open_schedules.*

class OpenSchedulesFragment(private val vm: HomeViewModel) :
    Fragment(), AuthInterface {
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
        viewModel.authInterface = this

        viewModel.openSchedulesLiveData.observe(viewLifecycleOwner, Observer {
            with(openRecyclerView) {
                adapter = OpenSchedulesAdapter(it)
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        })
    }

    override fun onStarted() {
        CustomDialog(context!!, viewModel.dbStatusLiveData, this).show()
    }

    override fun onSuccess() {
        viewModel.dbStatusLiveData.value = Pair(1, null)
    }

    override fun onFailure(message: String?) {
        viewModel.dbStatusLiveData.value = Pair(2, message)

    }
}