package com.podeli.podeliwidget

import android.content.Context
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormatUtils {

    private fun formatMoney(
        context: Context,
        amount: Double,
        currency: Currency = defaultCurrency
    ): String {
        return getMoneyFormat(currency).format(amount)
    }

    private val moneyFormat: MutableMap<Currency, NumberFormat> = mutableMapOf()

    private fun getMoneyFormat(currency: Currency) = moneyFormat.getOrPut(currency) {
        NumberFormat.getCurrencyInstance(defaultLocale).also {
            it.currency = currency
            it.minimumFractionDigits = 0
            it.maximumFractionDigits = 2
        }
    }

    private val defaultLocale = Locale("ru")
    private val defaultCurrency = Currency.getInstance("RUB")

    fun Context.formatMoney(amount: Double) = formatMoney(this, amount)

    fun Context.formatDate(date: Date): String {
        return SimpleDateFormat("d MMMM", defaultLocale).format(date)
    }
}