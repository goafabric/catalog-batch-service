package org.goafabric.catalog.job.insurance

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface InsuranceRepository : CrudRepository<InsuranceEo, String> {
    @Query("SELECT * FROM catalog.insurance WHERE code = :code")
    fun findByCode(code: String): MutableList<InsuranceEo>

    @Query("SELECT * FROM catalog.insurance WHERE UPPER(display) LIKE CONCAT(UPPER(:display), '%')")
    fun findByDisplayStartsWith(display: String): MutableList<InsuranceEo>
}
