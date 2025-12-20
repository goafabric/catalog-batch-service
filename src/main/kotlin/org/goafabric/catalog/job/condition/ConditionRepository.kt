package org.goafabric.catalog.job.condition

import org.springframework.data.repository.CrudRepository


interface ConditionRepository : CrudRepository<ConditionEo, String> {
    fun findByCode(code: String?): ConditionEo?

    fun findByDisplayStartsWith(display: String?): MutableList<ConditionEo?>?
}
