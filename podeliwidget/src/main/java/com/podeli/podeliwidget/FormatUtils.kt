package com.podeli.podeliwidget

import android.content.Context
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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

    private inline val Instant.asLocalDateTime
        get() = toLocalDateTime(TimeZone.currentSystemDefault())

    private inline val LocalDateTime.asLegacyDate
        get() = Date(year - 1900, monthNumber - 1, dayOfMonth, hour, minute, second)

    private fun formatDate(context: Context, date: LocalDateTime): String {
        return SimpleDateFormat("d MMMM", defaultLocale).format(date.asLegacyDate)
    }

    fun Context.formatDate(instant: Instant) = formatDate(this, instant.asLocalDateTime)
}