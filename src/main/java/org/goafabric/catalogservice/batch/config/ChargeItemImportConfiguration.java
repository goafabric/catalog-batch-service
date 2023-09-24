package org.goafabric.catalogservice.batch.config;

import org.goafabric.catalogservice.batch.JobCompletionListener;
import org.goafabric.catalogservice.repository.ChargeItemRepository;
import org.goafabric.catalogservice.repository.entity.ChargeItemEo;
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
public class ChargeItemImportConfiguration {

    @Bean
    public Job chargeItemJob(Step chargeItemStep, JobCompletionListener listener, JobRepository jobRepository) {
        return new JobBuilder("chargeItemJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener).flow(chargeItemStep).end()
                .build();
    }

    @Bean
    public Step chargeItemStep(ItemReader<ChargeItemEo> diagnosisItemReader,
                           ItemWriter<ChargeItemEo> ChargeItemEoItemWriter,
                           JobRepository jobRepository,
                           PlatformTransactionManager ptm) {
        return new StepBuilder("chargeItemStep", jobRepository)
                .<ChargeItemEo, ChargeItemEo>chunk(2, ptm)
                .reader(diagnosisItemReader)
                .writer(ChargeItemEoItemWriter)
                .build();
    }

    @Bean
    public ItemReader<ChargeItemEo> chargeItemItemReader() {
        return new FlatFileItemReaderBuilder<ChargeItemEo>()
                .name("InsuranceItemReader")
                .resource(new ClassPathResource("catalogs/goae.csv"))
                .delimited().delimiter(";")
                .names(new String[]{"code", "display", "price"})
                .fieldSetMapper(fieldSet ->
                        new ChargeItemEo(fieldSet.readString("code"), fieldSet.readString("display"), fieldSet.readDouble("price")))
                .build();

    }
    
    @Bean
    public ItemWriter<ChargeItemEo> chargeItemItemWriter(ChargeItemRepository repository) {
        return repository::saveAll;
    }

}
