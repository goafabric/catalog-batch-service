package org.goafabric.catalog.job.insurance;

import org.goafabric.catalog.job.JobCompletionListener;
import org.goafabric.catalog.job.StepCompletionListener;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class InsuranceImportConfiguration {

    @Bean
    public Job insuranceJob(Step insuranceStep, JobCompletionListener listener, JobRepository jobRepository) {
        return new JobBuilder("insuranceJob", jobRepository)
                //.incrementer(new RunIdIncrementer())
                .listener(listener).flow(insuranceStep).end()
                .build();
    }

    @Bean
    public Step insuranceStep(ItemReader<InsuranceEo> diagnosisItemReader,
                              ItemWriter<InsuranceEo> insuranceEoItemWriter,
                              JobRepository jobRepository) {
        return new StepBuilder("insuranceStep", jobRepository)
                .<InsuranceEo, InsuranceEo>chunk(2)
                .reader(diagnosisItemReader)
                .writer(insuranceEoItemWriter)
                .listener(new StepCompletionListener())
                .build();
    }

    @Bean
    public ItemReader<InsuranceEo> insuranceItemReader() {
        return new FlatFileItemReaderBuilder<InsuranceEo>()
                .name("insuranceItemReader")
                .resource(new ClassPathResource("catalogs/insurance_pkv.csv"))
                .delimited().delimiter(";")
                .names("code", "display", "shortname")
                .fieldSetMapper(fieldSet ->
                        new InsuranceEo(fieldSet.readString("code"), fieldSet.readString("display"), fieldSet.readString("shortname")))
                .build();

    }

    @Bean
    public ItemWriter<InsuranceEo> insuranceItemWriter(InsuranceRepository repository) {
        return repository::saveAll;
    }

}
