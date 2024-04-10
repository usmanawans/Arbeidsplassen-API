package no.nlod.arbeidsplassen.services

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class StatisticsService (
        @Qualifier("calendarService") private val calendarService: CalendarService
){
    @Throws(Exception::class)
    fun init() {
        calendarService.startFillingDatesInWeeks()
        calendarService.fetchAdsOnWeeklyPeriods()
        calendarService.printResultsWeekly()
    }
}