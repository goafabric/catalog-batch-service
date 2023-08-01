package org.goafabric.catalogservice.batch.config;

import org.goafabric.catalogservice.batch.JobCompletionListener;
import org.goafabric.catalogservice.service.repository.ConditionRepository;
import org.goafabric.catalogservice.service.repository.entity.ConditionEo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.UUID;

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
                //.fieldSetMapper(new RecordFieldSetMapper(ConditionEo.class))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{ setTargetType(ConditionEo.class); }})
                .build();

    }
    
    @Bean
    public ItemWriter<ConditionEo> conditionItemWriter(ConditionRepository repository) {
        return chunks -> chunks.getItems().forEach(conditionEo -> {
            conditionEo.id = UUID.randomUUID().toString();
            repository.save(conditionEo);
        });
    }

}
