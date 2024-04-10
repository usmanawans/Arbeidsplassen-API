package no.nlod.arbeidsplassen.model
data class Ad(
        val uuid: String,
        val published: String,
        val updated: String,
        val expires: String,
        val title: String,
        val description: String)