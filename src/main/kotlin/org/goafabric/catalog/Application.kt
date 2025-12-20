package org.goafabric.catalog

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
@EnableJdbcJobRepository(tablePrefix = "catalog.BATCH_")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}