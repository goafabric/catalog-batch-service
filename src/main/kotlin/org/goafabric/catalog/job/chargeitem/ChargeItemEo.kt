package org.goafabric.catalog.job.chargeitem

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "chargeitem", schema = "catalog") //@Document("chargeitem")
data class ChargeItemEo(
    @Id val id: String = UUID.randomUUID().toString(),
    @Version val version: Long? = null,
    val code: String?,
    val display: String?,
    val price: Double?
)