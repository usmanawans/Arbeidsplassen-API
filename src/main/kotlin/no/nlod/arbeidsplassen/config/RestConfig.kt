package no.nlod.arbeidsplassen.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestConfig {

    @Value("\${no.nav.arbeidsplassen.api.base.url}")
    lateinit var baseUrl: String;

    @Value("\${no.nav.arbeidsplassen.api.token}")
    lateinit var token: String;

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder().baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer $token")
                .defaultHeader("Accept", "application/json")
                .build()
    }
}