package org.goafabric.catalog.job

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.job.Job
import org.springframework.batch.core.job.JobExecutionException
import org.springframework.batch.core.job.parameters.JobParameters
import org.springframework.batch.core.launch.JobOperator
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class JobLauncherStarter(
    private val jobOperator: JobOperator,
    private val jobRepository: JobRepository,
    private val jobs: MutableList<Job?>,
    private val applicationContext: ApplicationContext,
    @param:Value("\${database.provisioning.goals:}"
    ) private val goals: String
) : CommandLineRunner {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun run(vararg args: String) {
        if (goals.contains("-import-catalog-data")) {
            jobs.forEach(Consumer { job: Job? ->
                try {
                    if (jobRepository.getJobInstance(job!!.getName(), JobParameters()) == null) {
                        jobOperator.start(job, JobParameters())
                    }
                } catch (e: JobExecutionException) {
                    throw IllegalStateException(e)
                }
            })
        }

        if (goals.contains("-terminate")) {
            log.info("Terminating app ...")
            SpringApplication.exit(
                applicationContext,
                ExitCodeGenerator { 0 }) //if an exception is raised, spring will automatically terminate with 1
        }
    }
}
