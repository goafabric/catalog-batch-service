package org.goafabric.catalog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecutionException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobLauncherStarter implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final JobOperator jobOperator;

    private final List<Job> jobs;

    private final ApplicationContext applicationContext;

    private final String goals;

    public JobLauncherStarter(JobOperator jobOperator, List<Job> jobs, ApplicationContext applicationContext, @Value("${database.provisioning.goals:}") String goals) {
        this.jobOperator = jobOperator;
        this.jobs = jobs;
        this.applicationContext = applicationContext;
        this.goals = goals;
    }

    @Override
    public void run(String... args) throws Exception {
        if ((args.length > 0) && ("-check-integrity".equals(args[0]))) { return; }

        if (goals.contains("-import-catalog-data")) {
            jobs.forEach(job -> {
                try {
                    jobOperator.start(job, new JobParameters());
                } catch (JobExecutionException e) {
                    throw new IllegalStateException(e);
                }
            });
        }

        if (goals.contains("-terminate")) {
            log.info("Terminating app ...");
            SpringApplication.exit(applicationContext, () -> 0); //if an exception is raised, spring will automatically terminate with 1
        }
    }
}
