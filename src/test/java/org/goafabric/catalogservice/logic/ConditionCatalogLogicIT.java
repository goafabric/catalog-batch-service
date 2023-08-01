package org.goafabric.catalogservice.logic;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.logic.CrudLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConditionCatalogLogicIT {
    @Autowired
    private CrudLogic<Condition> diagnosisCatalogLogic;

    @Test
    void search() {
        assertThat(diagnosisCatalogLogic.search("")).isNotNull().isNotEmpty();
    }
}
