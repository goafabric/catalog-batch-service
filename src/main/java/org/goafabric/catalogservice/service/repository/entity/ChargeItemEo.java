package org.goafabric.catalogservice.service.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "chargeitem", schema = "catalog")
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
