package org.goafabric.catalogservice.batch.config;

import org.goafabric.catalogservice.batch.JobCompletionListener;
import org.goafabric.catalogservice.repository.InsuranceRepository;
import org.goafabric.catalogservice.repository.entity.InsuranceEo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class InsuranceImportConfiguration {

    @Bean
    public Job insuranceJob(Step insuranceStep, JobCompletionListener listener, JobRepository jobRepository) {
        return new JobBuilder("insuranceJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener).flow(insuranceStep).end()
                .build();
    }

    @Bean
    public Step insuranceStep(ItemReader<InsuranceEo> diagnosisItemReader,
                           ItemWriter<InsuranceEo> InsuranceEoItemWriter,
                           JobRepository jobRepository,
                           PlatformTransactionManager ptm) {
        return new StepBuilder("insuranceStep", jobRepository)
                .<InsuranceEo, InsuranceEo>chunk(2, ptm)
                .reader(diagnosisItemReader)
                .writer(InsuranceEoItemWriter)
                .build();
    }

    @Bean
    public ItemReader<InsuranceEo> insuranceItemReader() {
        return new FlatFileItemReaderBuilder<InsuranceEo>()
                .name("insuranceItemReader")
                .resource(new ClassPathResource("catalogs/insurance_pkv.csv"))
                .delimited().delimiter(";")
                .names(new String[]{"code", "display", "shortname"})
                .fieldSetMapper(fieldSet ->
                        new InsuranceEo(fieldSet.readString("code"), fieldSet.readString("display"), fieldSet.readString("shortname")))
                .build();

    }

    @Bean
    public ItemWriter<InsuranceEo> insuranceItemWriter(InsuranceRepository repository) {
        return repository::saveAll;
    }

}
