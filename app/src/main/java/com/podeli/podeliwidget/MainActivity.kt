package com.podeli.podeliwidget

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentWidgetProcent = findViewById<PodeliWidget>(R.id.podeli_widget_procent)
        val paymentWidget = findViewById<PodeliWidget>(R.id.podeli_widget)

        paymentWidgetProcent.setPaymentAmount()
        paymentWidget.setPaymentAmount(3000.0)
    }
}