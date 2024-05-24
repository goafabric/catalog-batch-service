package org.goafabric.catalog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChargeItemControllerIT {

    @Autowired
    private ChargeItemController chargeItemController;

    @Test
    void findByCode() {
        assertThat(chargeItemController.findByCode("GOAE1")).isNotNull();
    }

    @Test
    void findByDisplay() {
        assertThat(chargeItemController.findByDisplay("")).isNotNull().isNotEmpty();
    }
}
