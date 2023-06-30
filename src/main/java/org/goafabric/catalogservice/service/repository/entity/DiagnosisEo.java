package org.goafabric.catalogservice.service.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//@Entity @Table(name = "diagnosis")
@Table(name = "diagnosis", schema = "catalog")
public class DiagnosisEo {
    //@org.springframework.data.annotation.Id @jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    public String id;

    public String code;
    public String display;
    public String shortname;
}
