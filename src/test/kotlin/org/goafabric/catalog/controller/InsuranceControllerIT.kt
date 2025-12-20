package org.goafabric.catalog.controller

import org.assertj.core.api.Assertions
import org.goafabric.catalog.job.insurance.InsuranceEo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class InsuranceControllerIT {
    @Autowired
    private val insuranceController: InsuranceController? = null

    @Test
    fun findByCode() {
        Assertions.assertThat<InsuranceEo>(insuranceController!!.findByCode("168140040")).isNotNull()
    }

    @Test
    fun search() {
        Assertions.assertThat<InsuranceEo?>(insuranceController!!.findByDisplay("")).isNotNull().isNotEmpty()
    }
}
