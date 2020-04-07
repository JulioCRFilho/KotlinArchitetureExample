package com.example.uds.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.uds.R
import com.example.uds.api.AuthInterface
import com.example.uds.databinding.FragmentOpenSchedulesBinding
import com.example.uds.utils.CustomDialog
import com.example.uds.viewModel.HomeViewModel

class OpenSchedulesFragment(private val vm: HomeViewModel, private val activity: Activity) :
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
        val binding: FragmentOpenSchedulesBinding =
            DataBindingUtil.setContentView(activity, R.layout.fragment_open_schedules)
        viewModel = vm
        binding.viewModel = viewModel
        viewModel.authInterface = this
    }

    override fun onStarted() {
        CustomDialog(activity, viewModel.dbStatusLiveData, this).show()
    }

    override fun onSuccess() {
        viewModel.dbStatusLiveData.value = Pair(1, null)
    }

    override fun onFailure(message: String?) {
        viewModel.dbStatusLiveData.value = Pair(2, message)
    }
}