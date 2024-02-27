package com.podeli.podeliwidget

import java.util.Date

object DatesGenerator {
    private val instant = System.currentTimeMillis()

    val dates = listOf(
        Date(instant),
        Date(instant + 14 * 24 * 60 * 60 * 1000),
        Date(instant + 28 * 24 * 60 * 60 * 1000),
        Date(instant + 42 * 24 * 60 * 60 * 1000)
    )
}