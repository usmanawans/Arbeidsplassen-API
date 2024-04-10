package no.nlod.arbeidsplassen.services

import com.fasterxml.jackson.databind.ObjectMapper
import no.nlod.arbeidsplassen.model.Ad
import no.nlod.arbeidsplassen.model.AdsStatistics
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.ZonedDateTime
import java.util.*


@Service
class CalendarService (
        @Qualifier("adsService") private val adsService: AdsService
) {

    var NUMBER_OF_WEEKS = 24

    private var weeks = ArrayList<AdsStatistics>()
    private var results = ArrayList<AdsStatistics>()

    fun startFillingDatesInWeeks() {
        val calendar = GregorianCalendar()
        calendar.setFirstDayOfWeek(Calendar.MONDAY)

        // start from 24 weeks ago
        var start = ZonedDateTime.now().minusWeeks(NUMBER_OF_WEEKS.toLong()).with(DayOfWeek.MONDAY)
        for (i in 1..NUMBER_OF_WEEKS) {
            val nextWeekStart: Date = Date.from(start.toInstant())
            val nextWeekEnd: Date = Date.from(start.plusWeeks(1).toInstant())
            calendar.setTime(nextWeekStart)
            val weekNr = calendar[Calendar.WEEK_OF_YEAR]
            val year = calendar[Calendar.YEAR]
            val week = AdsStatistics(weekNr, nextWeekStart, nextWeekEnd, year)
            weeks.add(week)
            start = start.plusWeeks(1)
        }
    }

    @Throws(Exception::class)
    fun fetchAdsOnWeeklyPeriods() {
        for (week in weeks) {
            val ads: List<Ad> = adsService.getAdsForWeek(week.getPeriod())
            val kotlinCounter: Int = seaarchOccurances("kotlin", ads)
            val javaCounter: Int = seaarchOccurances("java", ads)
            week.kotlinCounter = kotlinCounter
            week.javaCounter = javaCounter
            results.add(week)
        }
    }


    fun seaarchOccurances(searchTerm: String, advertisements: List<Ad?>): Int {
        var counter = 0
        for (advertisement in advertisements) {
            if (advertisement!!.title.lowercase().contains(searchTerm.lowercase(Locale.getDefault())) ||
                    advertisement.description.lowercase().contains(searchTerm)) {
                counter++
            }
        }
        return counter
    }


    @Throws(java.lang.Exception::class)
    fun printResultsWeekly() {
        results.map { result -> println(result) }
    }

    @Throws(java.lang.Exception::class)
    fun getResultsWeekly() : ArrayList<AdsStatistics> {
        return results
    }

}