package org.goafabric.catalog.controller

import org.assertj.core.api.Assertions
import org.goafabric.catalog.job.chargeitem.ChargeItemEo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ChargeItemControllerIT {
    @Autowired
    private val chargeItemController: ChargeItemController? = null

    @Test
    fun findByCode() {
        Assertions.assertThat<ChargeItemEo?>(chargeItemController!!.findByCode("GOAE1")).isNotNull()
    }

    @Test
    fun findByDisplay() {
        Assertions.assertThat<ChargeItemEo?>(chargeItemController!!.findByDisplay("")).isNotNull().isNotEmpty()
    }
}
