package com.example.uds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uds.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.title = "Início"
        setSupportActionBar(toolbar)
    }
}
