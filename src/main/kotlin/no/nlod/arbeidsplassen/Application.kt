package no.nlod.arbeidsplassen

import no.nlod.arbeidsplassen.services.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application: CommandLineRunner {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun run(vararg args: String?) {
        statisticsService.init();
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}



