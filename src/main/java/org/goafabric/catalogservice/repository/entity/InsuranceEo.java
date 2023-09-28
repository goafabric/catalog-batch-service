package org.goafabric.catalogservice.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "insurance", schema = "catalog")
@Document("insurance")
public record InsuranceEo(
    @Id String id,
    @Version Long version,
    String code,
    String display,
    String shortname
) {
    public InsuranceEo(String code, String display, String shortname) {
        this(UUID.randomUUID().toString(), null, code, display, shortname);
    }
}
