package com.podeli.podeliwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentWidgetProcent = findViewById<PodeliWidget>(R.id.podeli_widget_procent)
        val paymentWidget = findViewById<PodeliWidget>(R.id.podeli_widget)

        paymentWidgetProcent.setPaymentAmount()
        paymentWidget.setPaymentAmount(32.33)
    }
}