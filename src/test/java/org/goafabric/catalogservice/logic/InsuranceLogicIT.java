package org.goafabric.catalogservice.logic;

import org.goafabric.catalogservice.service.logic.InsuranceLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InsuranceLogicIT {
    @Autowired
    private InsuranceLogic insuranceCatalogLogic;


    @Test
    void search() {
        assertThat(insuranceCatalogLogic.search("")).isNotNull().isNotEmpty();
    }
}
