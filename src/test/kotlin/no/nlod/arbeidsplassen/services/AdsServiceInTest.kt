package no.nlod.arbeidsplassen.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class AdsServiceInTest (
        @Qualifier("adsService") private val adsService: AdsService
){

    @Test
    @Throws(Exception::class)
    fun whenCallingGetUserDetailsThenClientMakesCorrectCall() {
        val ads = adsService.getAdsForWeek("")
        assertThat(ads).isNotEmpty
        assertThat(ads.size).isGreaterThan(0)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testThatVavApiIsReturningPageNumber() {
        val totalPages: Int = adsService.getAdsPageCount("[2010-06-12,2022-08-02)")
        Assertions.assertEquals(50, totalPages)
    }
}