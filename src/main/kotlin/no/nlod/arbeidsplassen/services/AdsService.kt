package no.nlod.arbeidsplassen.services

import no.nlod.arbeidsplassen.model.Ad
import no.nlod.arbeidsplassen.model.AdsResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.logging.Logger


@Service
class AdsService(
        @Qualifier("restClient") private val restClient: RestClient
) {

    var logger: Logger = Logger.getLogger(AdsService::class.java.getName())

    @Throws(Exception::class)
    fun getAdsForWeek(weeklyDuration: String): List<Ad> {
        val totalPages: Int = getAdsPageCount(weeklyDuration)
        val advertisements: MutableList<Ad> = ArrayList<Ad>()

        for(page in 1..totalPages) {
            val respons = restClient.get()
                    .uri("/ads?size=1000&published=$weeklyDuration&page=$page")
                    .retrieve().toEntity(AdsResponse::class.java)

            respons.body?.let { advertisements.addAll(it.content) }
        }
        logger.info("Total ${advertisements.size} found for week $weeklyDuration")
        return advertisements;
    }

    @Throws(java.lang.Exception::class)
    fun getAdsPageCount(weeklyDuration: String): Int {
        var pageNr = 1

        val respons = restClient.get()
                .uri("/ads?size=1000&?published=$weeklyDuration")
                .retrieve().toEntity(AdsResponse::class.java)

        pageNr = respons.body?.totalPages ?: 1

        return pageNr
    }
}