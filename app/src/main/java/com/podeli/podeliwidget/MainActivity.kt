package com.podeli.podeliwidget

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentWidgetProcent = findViewById<PodeliWidget>(R.id.podeli_widget_procent)
        val paymentWidget = findViewById<PodeliWidget>(R.id.podeli_widget)
        val amountEditText = findViewById<EditText>(R.id.amount_edit_text)

        paymentWidgetProcent.setPaymentAmount()

        paymentWidget.setPaymentAmount(1000.31)

        amountEditText.addTextChangedListener {
            val amount = it.toString().toDoubleOrNull()
            if (it == null || it.isBlank() || amount == null) {
                paymentWidget.setPaymentAmount()
            } else {
                paymentWidget.setPaymentAmount(amount)
            }
        }
    }
}