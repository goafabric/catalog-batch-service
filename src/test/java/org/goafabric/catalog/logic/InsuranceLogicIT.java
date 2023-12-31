package org.goafabric.catalog.logic;

import org.goafabric.catalog.controller.InsuranceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InsuranceLogicIT {
    @Autowired
    private InsuranceController insuranceController;


    @Test
    void search() {
        assertThat(insuranceController.findByDisplay("")).isNotNull().isNotEmpty();
    }
}
