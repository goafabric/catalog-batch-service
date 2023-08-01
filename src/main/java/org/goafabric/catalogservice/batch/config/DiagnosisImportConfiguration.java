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
public class DiagnosisImportConfiguration {

    @Bean
    public Job DiagnosisEoJob(Step diagnosisStep, JobCompletionListener listener, JobRepository jobRepository) {
        return new JobBuilder("diagnosisJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener).flow(diagnosisStep).end()
                .build();
    }

    @Bean(name = "diagnosisStep")
    public Step diagnosisStep(ItemReader<ConditionEo> diagnosisItemReader,
                           ItemWriter<ConditionEo> DiagnosisEoItemWriter,
                           JobRepository jobRepository,
                           PlatformTransactionManager ptm) {
        return new StepBuilder("diagnosisStep", jobRepository)
                .<ConditionEo, ConditionEo>chunk(2, ptm)
                .reader(diagnosisItemReader)
                .writer(DiagnosisEoItemWriter)
                .build();
    }

    @Bean
    public ItemReader<ConditionEo> diagnosisItemReader() {
        return new FlatFileItemReaderBuilder<ConditionEo>()
                .name("diagnosisItemReader")
                .resource(new ClassPathResource("catalogs/icd10.csv"))
                .delimited().delimiter(";")
                .names(new String[]{"code", "display", "shortname"})
                //.fieldSetMapper(new RecordFieldSetMapper(DiagnosisEo.class))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ConditionEo>() {{
                    setTargetType(ConditionEo.class);
                }})
                .build();

    }
    
    @Bean
    public ItemWriter<ConditionEo> diagnosisItemWriter(ConditionRepository repository) {
        return chunks -> chunks.getItems().forEach(chunk -> {
            chunk.id = UUID.randomUUID().toString();
            repository.save(chunk);
        });
    }

}
