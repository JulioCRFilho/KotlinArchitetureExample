package com.example.uds.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setToolbar(toolbar: Toolbar, title: String, homeEnabled: Boolean = true) {
    toolbar.title = title
    this.setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
}