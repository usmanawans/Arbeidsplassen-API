package no.nlod.arbeidsplassen.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


data class AdsStatistics (
        val weekNr: Int,
        val startWeek: Date,
        val endWeek: Date,
        val year: Int) {

    var kotlinCounter: Int = 0
    var javaCounter: Int = 0

    operator fun compareTo(otherAdsStatistics: AdsStatistics): Int {
        return startWeek.compareTo(otherAdsStatistics.endWeek)
    }
    fun getPeriod(): String {
        val pattern: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return "[" + pattern.format(startWeek) + "," + pattern.format(endWeek) + ")"
    }

    override fun toString(): String {
        return ("Week: $weekNr ($year) \n Kotlin: $kotlinCounter \n Java: $javaCounter \n")
    }
}