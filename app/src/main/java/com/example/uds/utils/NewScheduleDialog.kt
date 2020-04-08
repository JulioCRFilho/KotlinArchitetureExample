package com.example.uds.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.uds.R
import com.example.uds.viewModel.HomeViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_new_schedule.*

class NewScheduleDialog(
    context: Context,
    private val viewModel: HomeViewModel
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_new_schedule)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        val scheduleTitle: TextInputEditText = schedule_title
        val scheduleIntro: TextInputEditText = schedule_intro
        val scheduleDesc: TextInputEditText = schedule_description

        schedule_autor.setText(viewModel.userName)

        button.setOnClickListener {
            if (scheduleTitle.text?.isEmpty()!! || scheduleIntro.text?.isEmpty()!! || scheduleDesc.text?.isEmpty()!!) {
                return@setOnClickListener Toast.makeText(
                    context,
                    "Todos os campos são obrigatórios",
                    Toast.LENGTH_SHORT
                ).show()
            }

            viewModel.writeToDB(
                scheduleTitle.text.toString(),
                scheduleIntro.text.toString(),
                scheduleDesc.text.toString()
            )

            this.dismiss()
        }
    }
}