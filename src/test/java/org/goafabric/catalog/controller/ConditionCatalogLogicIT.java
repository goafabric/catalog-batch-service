package org.goafabric.catalog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConditionCatalogLogicIT {
    @Autowired
    private ConditionController conditionController;

    @Test
    void findByCode() {
        assertThat(conditionController.findByCode("E66.00")).isNotNull();
    }

    @Test
    void search() {
        assertThat(conditionController.findByDisplay("")).isNotNull().isNotEmpty();
    }
}
