package org.goafabric.catalog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChargeItemControllerIT {

    @Autowired
    private ChargeItemController insuranceController;

    @Test
    void search() {
        assertThat(insuranceController.findByDisplay("")).isNotNull().isNotEmpty();
    }
}
