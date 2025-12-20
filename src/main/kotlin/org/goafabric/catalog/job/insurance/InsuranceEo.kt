package org.goafabric.catalog.job.insurance

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "insurance", schema = "catalog") //@Document("insurance")
data class InsuranceEo(
    @Id val id: String = UUID.randomUUID().toString(),
    @Version val version: Long? = null,
    val code: String?,
    val display: String?,
    val shortname: String?
)