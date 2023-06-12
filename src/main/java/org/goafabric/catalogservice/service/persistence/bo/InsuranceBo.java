package org.goafabric.catalogservice.service.persistence.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//@Entity @Table(name = "insurance")
@Table(name = "catalog.insurance")
public class InsuranceBo {

    //@org.springframework.data.annotation.Id @jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    public String id;

    public String code;
    public String display;
    public String shortname;
}