package org.goafabric.calleeservice.logic;

import org.goafabric.calleeservice.controller.dto.Diagnosis;
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
