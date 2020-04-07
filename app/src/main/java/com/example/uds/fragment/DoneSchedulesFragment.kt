package com.example.uds.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.uds.R
import com.example.uds.databinding.FragmentDoneSchedulesBinding
import com.example.uds.viewModel.HomeViewModel

class DoneSchedulesFragment(private val vm: HomeViewModel, private val activity: Activity) :
    Fragment() {
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_done_schedules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentDoneSchedulesBinding =
            DataBindingUtil.setContentView(activity, R.layout.fragment_done_schedules)
        viewModel = vm
        binding.viewModel = viewModel
    }
}