package com.podeli.podeliwidget

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus

object DatesGenerator {
    private val instant = Clock.System.now()
    private val systemTZ = TimeZone.currentSystemDefault()

    val dates = listOf(
        instant,
        instant.plus(14, DateTimeUnit.DAY, systemTZ),
        instant.plus(28, DateTimeUnit.DAY, systemTZ),
        instant.plus(42, DateTimeUnit.DAY, systemTZ),
    )
}