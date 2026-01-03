package org.goafabric.catalog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;

public class StepCompletionListener implements StepExecutionListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();
        if (BatchStatus.COMPLETED.equals(stepExecution.getStatus())) {
            log.info("{} completed ", stepName);
            return ExitStatus.COMPLETED;
        } else {
            log.error("There was a problem with step: {}", stepName);
            return ExitStatus.FAILED;
        }
    }

}

