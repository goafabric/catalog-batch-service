package org.goafabric.catalog.job.condition;

import org.goafabric.catalog.job.JobCompletionListener;
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
public class ConditionImportConfiguration {

    @Bean
    public Job ConditionEoJob(Step conditionStep, JobCompletionListener listener, JobRepository jobRepository) {
        return new JobBuilder("conditionJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener).flow(conditionStep).end()
                .build();
    }

    @Bean(name = "conditionStep")
    public Step conditionStep(ItemReader<ConditionEo> conditionItemReader,
                           ItemWriter<ConditionEo> ConditionEoItemWriter,
                           JobRepository jobRepository,
                           PlatformTransactionManager ptm) {
        return new StepBuilder("conditionStep", jobRepository)
                .<ConditionEo, ConditionEo>chunk(2, ptm)
                .reader(conditionItemReader)
                .writer(ConditionEoItemWriter)
                .build();
    }

    @Bean
    public ItemReader<ConditionEo> conditionItemReader() {
        return new FlatFileItemReaderBuilder<ConditionEo>()
                .name("conditionItemReader")
                .resource(new ClassPathResource("catalogs/icd10.csv"))
                .delimited().delimiter(";")
                .names(new String[]{"code", "display", "shortname"})
                .fieldSetMapper(fieldSet ->
                        new ConditionEo(fieldSet.readString("code"), fieldSet.readString("display"), fieldSet.readString("shortname")))
                .build();

    }
    
    @Bean
    public ItemWriter<ConditionEo> conditionItemWriter(ConditionRepository repository) {
        return repository::saveAll;
    }

}
