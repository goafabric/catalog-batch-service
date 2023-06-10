package org.goafabric.catalogservice.service.persistence.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity @Table(name = "diagnosis")
public class DiagnosisBo {
    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    public String code;
    public String display;
    public String shortname;
}
