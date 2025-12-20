package org.goafabric.catalog.job.insurance

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
class InsuranceImportConfiguration {
    @Bean
    fun insuranceJob(insuranceStep: Step, listener: JobCompletionListener?, jobRepository: JobRepository): Job? {
        return JobBuilder("insuranceJob", jobRepository) //.incrementer(new RunIdIncrementer())
            .listener(listener).flow(insuranceStep).end()
            .build()
    }

    @Bean
    fun insuranceStep(
        diagnosisItemReader: ItemReader<InsuranceEo>,
        insuranceEoItemWriter: ItemWriter<InsuranceEo>,
        jobRepository: JobRepository
    ): Step {
        return StepBuilder("insuranceStep", jobRepository)
            .chunk<InsuranceEo, InsuranceEo>(2)
            .reader(diagnosisItemReader)
            .writer(insuranceEoItemWriter)
            .listener(StepCompletionListener())
            .build()
    }

    @Bean
    fun insuranceItemReader(): ItemReader<InsuranceEo> {
        return FlatFileItemReaderBuilder<InsuranceEo>()
            .name("insuranceItemReader")
            .resource(ClassPathResource("catalogs/insurance_pkv.csv"))
            .delimited().delimiter(";")
            .names("code", "display", "shortname")
            .fieldSetMapper(FieldSetMapper { fieldSet: FieldSet? ->
                InsuranceEo(
                    code = fieldSet!!.readString("code"),
                    display = fieldSet.readString("display"),
                    shortname = fieldSet.readString("shortname")
                )
            })
            .build()
    }

    @Bean
    fun insuranceItemWriter(repository: InsuranceRepository): ItemWriter<InsuranceEo> {
        return ItemWriter { entities: Chunk<out InsuranceEo> -> repository.saveAll(entities) }
    }
}
