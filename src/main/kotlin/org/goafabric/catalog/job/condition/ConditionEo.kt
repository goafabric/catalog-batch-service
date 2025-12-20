package org.goafabric.catalog.job.condition

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "condition", schema = "catalog") //@Document("condition")
data class ConditionEo(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val version: Long? = null,
    val code: String?,
    val display: String?,
    val shortname: String?
)