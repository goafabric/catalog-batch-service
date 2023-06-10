package org.goafabric.catalogservice.service.persistence.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity @Table(name = "diagnosis")
public class DiagnosisBo {
    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;
    private String display;
    private String shortname;
}
