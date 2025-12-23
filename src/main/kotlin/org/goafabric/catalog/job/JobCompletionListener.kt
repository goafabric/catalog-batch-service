package org.goafabric.catalog.job

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.job.JobExecution
import org.springframework.batch.core.listener.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class JobCompletionListener : JobExecutionListener {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished: {}", jobExecution.jobInstance.jobName)
        } else {
            log.error("There was a problem with jor job: {}", jobExecution.getJobInstance().getJobName())
        }
    }
}
