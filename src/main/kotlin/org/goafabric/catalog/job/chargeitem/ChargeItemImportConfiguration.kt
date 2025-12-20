package org.goafabric.catalog.job.chargeitem

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
class ChargeItemImportConfiguration {
    @Bean
    fun chargeItemJob(chargeItemStep: Step, listener: JobCompletionListener?, jobRepository: JobRepository): Job? {
        return JobBuilder("chargeItemJob", jobRepository) //.incrementer(new RunIdIncrementer())
            .listener(listener).flow(chargeItemStep).end()
            .build()
    }

    @Bean
    fun chargeItemStep(
        diagnosisItemReader: ItemReader<ChargeItemEo>,
        chargeItemEoItemWriter: ItemWriter<ChargeItemEo>,
        jobRepository: JobRepository
    ): Step {
        return StepBuilder("chargeItemStep", jobRepository)
            .chunk<ChargeItemEo, ChargeItemEo>(2)
            .reader(diagnosisItemReader)
            .writer(chargeItemEoItemWriter)
            .listener(StepCompletionListener())
            .build()
    }

    @Bean
    fun chargeItemItemReader(): ItemReader<ChargeItemEo> {
        return FlatFileItemReaderBuilder<ChargeItemEo>()
            .name("InsuranceItemReader")
            .resource(ClassPathResource("catalogs/goae.csv"))
            .delimited().delimiter(";")
            .names("code", "display", "price")
            .fieldSetMapper(FieldSetMapper { fieldSet: FieldSet? ->
                ChargeItemEo(
                    code = fieldSet!!.readString("code"),
                    display = fieldSet.readString("display"),
                    price = fieldSet.readDouble("price")
                )
            })
            .build()
    }

    @Bean
    fun chargeItemItemWriter(repository: ChargeItemRepository): ItemWriter<ChargeItemEo> {
        return ItemWriter { entities: Chunk<out ChargeItemEo> -> repository.saveAll(entities) }
    }
}
