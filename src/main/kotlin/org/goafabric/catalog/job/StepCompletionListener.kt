package org.goafabric.catalog.job

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.listener.StepExecutionListener
import org.springframework.batch.core.step.StepExecution

class StepCompletionListener : StepExecutionListener {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        val stepName = stepExecution.getStepName()
        if (BatchStatus.COMPLETED == stepExecution.getStatus()) {
            log.info("{} completed ", stepName)
            return ExitStatus.COMPLETED
        } else {
            log.error("There was a problem with step: {}", stepName)
            return ExitStatus.FAILED
        }
    }
}

