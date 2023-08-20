package com.sahrul.moviecatalogue.utils

import kotlin.math.roundToInt

fun Double.roundOffDecimal(): Double =  when {
    isNaN() -> throw IllegalArgumentException("Cannot round NaN value.")
    this > Double.MAX_VALUE -> Double.MAX_VALUE
    this < Double.MIN_VALUE -> Double.MIN_VALUE
    else -> {
        (this * 10.0).roundToInt() / 10.0
    }
}