package com.android.template

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import template.android.com.data.DataKotlinInvoker
import template.android.com.device.DeviceKotlinInvoker
import template.android.com.domain.DomainKotlinInvoker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Hello world toast", Toast.LENGTH_SHORT).show()

        // To ensure all modules are well connected.
        DataKotlinInvoker().invoke()
        DeviceKotlinInvoker().invoke()
        DomainKotlinInvoker().invoke()
    }
}
