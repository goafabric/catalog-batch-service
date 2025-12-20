package org.goafabric.catalog.controller

import org.assertj.core.api.Assertions
import org.goafabric.catalog.job.condition.ConditionEo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ConditionCatalogLogicIT {
    @Autowired
    private val conditionController: ConditionController? = null

    @Test
    fun findByCode() {
        Assertions.assertThat<ConditionEo?>(conditionController!!.findByCode("E66.00")).isNotNull()
    }

    @Test
    fun search() {
        Assertions.assertThat<ConditionEo?>(conditionController!!.findByDisplay("")).isNotNull().isNotEmpty()
    }
}
