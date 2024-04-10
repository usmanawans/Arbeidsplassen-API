package no.nlod.arbeidsplassen.controllers

import no.nlod.arbeidsplassen.model.AdsStatistics
import no.nlod.arbeidsplassen.services.CalendarService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AdsRestController(@Qualifier("calendarService") private val calendarService: CalendarService) {
    @GetMapping(value = ["/"], produces = ["application/json"])
    fun getAds() : ArrayList<AdsStatistics> {

        calendarService.startFillingDatesInWeeks()
        calendarService.fetchAdsOnWeeklyPeriods()

        return calendarService.getResultsWeekly()
    }
}