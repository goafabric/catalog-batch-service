package org.goafabric.catalogservice.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogImporter implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private List<Job> jobs;

    @Value("${database.provisioning.goals:}")
    String goals;


    @Override
    public void run(String... args) throws Exception {
        if ((args.length > 0) && ("-check-integrity".equals(args[0]))) { return; }

        //if (goals.contains("-import-catalog-data")) {
        jobs.forEach(job -> {
            try {
                jobLauncher.run(job, new JobParameters());
            } catch (JobExecutionException e) {
                throw new RuntimeException(e);
            }
        });


        /*
        if (goals.contains("-terminate")) {
            log.info("Terminating app ...");
            SpringApplication.exit(applicationContext, () -> 0); //if an exception is raised, spring will automatically terminate with 1
        }
         */
    }
}
