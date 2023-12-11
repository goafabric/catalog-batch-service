package org.goafabric.catalog.job.chargeitem;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "chargeitem", schema = "catalog")
@Document("chargeitem")
public record ChargeItemEo(
    @Id String id,
    @Version Long version,
    String code,
    String display,
    Double price
) {
    public ChargeItemEo(String code, String display, Double price) {
        this(UUID.randomUUID().toString(), null, code, display, price);
    }
}
