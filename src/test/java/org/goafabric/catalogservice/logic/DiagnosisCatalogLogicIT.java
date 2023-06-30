package org.goafabric.catalogservice.logic;

import org.goafabric.catalogservice.service.controller.vo.Diagnosis;
import org.goafabric.catalogservice.service.logic.CrudLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DiagnosisCatalogLogicIT {
    @Autowired
    private CrudLogic<Diagnosis> diagnosisCatalogLogic;

    @Test
    void search() {
        assertThat(diagnosisCatalogLogic.search("")).isNotNull().isNotEmpty();
    }
}
