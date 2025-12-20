package org.goafabric.catalog.job.condition

import org.goafabric.catalog.job.JobCompletionListener
import org.goafabric.catalog.job.StepCompletionListener
import org.springframework.batch.core.job.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.Step
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.infrastructure.item.Chunk
import org.springframework.batch.infrastructure.item.ItemReader
import org.springframework.batch.infrastructure.item.ItemWriter
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper
import org.springframework.batch.infrastructure.item.file.transform.FieldSet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class ConditionImportConfiguration {
    @Bean
    fun conditionEoJob(conditionStep: Step, listener: JobCompletionListener?, jobRepository: JobRepository): Job? {
        return JobBuilder("conditionJob", jobRepository) //.incrementer(new RunIdIncrementer())
            .listener(listener).flow(conditionStep).end()
            .build()
    }

    @Bean(name = ["conditionStep"])
    fun conditionStep(
        conditionItemReader: ItemReader<ConditionEo>,
        conditionEoItemWriter: ItemWriter<ConditionEo>,
        jobRepository: JobRepository
    ): Step {
        return StepBuilder("conditionStep", jobRepository)
            .chunk<ConditionEo, ConditionEo>(2)
            .reader(conditionItemReader)
            .writer(conditionEoItemWriter)
            .listener(StepCompletionListener())
            .build()
    }

    @Bean
    fun conditionItemReader(): ItemReader<ConditionEo> {
        return FlatFileItemReaderBuilder<ConditionEo>()
            .name("conditionItemReader")
            .resource(ClassPathResource("catalogs/icd10.csv"))
            .delimited().delimiter(";")
            .names("code", "display", "shortname")
            .fieldSetMapper(FieldSetMapper { fieldSet: FieldSet? ->
                ConditionEo(
                    code = fieldSet!!.readString("code"),
                    display = fieldSet.readString("display"),
                    shortname = fieldSet.readString("shortname")
                )
            })
            .build()
    }

    @Bean
    fun conditionItemWriter(repository: ConditionRepository): ItemWriter<ConditionEo> {
        return ItemWriter { entities: Chunk<out ConditionEo> -> repository.saveAll(entities) }
    }
}
