package org.goafabric.catalog.job.chargeitem

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ChargeItemRepository : CrudRepository<ChargeItemEo, String> {
    @Query("SELECT * FROM catalog.chargeitem WHERE code = :code")
    fun findByCode(code: String): ChargeItemEo

    @Query("SELECT * FROM catalog.chargeitem WHERE UPPER(display) LIKE CONCAT(UPPER(:display), '%')")
    fun findByDisplayStartsWith(display: String): MutableList<ChargeItemEo>
}
