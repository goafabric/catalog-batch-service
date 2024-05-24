package org.goafabric.catalog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InsuranceControllerIT {
    @Autowired
    private InsuranceController insuranceController;

    @Test
    void findByCode() {
        assertThat(insuranceController.findByCode("168140040")).isNotNull();
    }

    @Test
    void search() {
        assertThat(insuranceController.findByDisplay("")).isNotNull().isNotEmpty();
    }
}
