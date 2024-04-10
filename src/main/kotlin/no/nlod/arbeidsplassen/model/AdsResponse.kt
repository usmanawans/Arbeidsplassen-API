package no.nlod.arbeidsplassen.model

data class AdsResponse(
        val content: List<Ad>,
        val totalPages: Int,
        val totalElements: Int)