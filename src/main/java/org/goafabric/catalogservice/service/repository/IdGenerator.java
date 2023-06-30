package org.goafabric.catalogservice.service.repository;

import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.goafabric.catalogservice.service.repository.entity.DiagnosisEo;
import org.goafabric.catalogservice.service.repository.entity.InsuranceEo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@Configuration
public class IdGenerator {
    @Bean
    BeforeConvertCallback<ChargeItemEo> beforeConvertCallback1() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }

    @Bean
    BeforeConvertCallback<DiagnosisEo> beforeConvertCallback2() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }
    @Bean
    BeforeConvertCallback<InsuranceEo> beforeConvertCallback3() {
        return aggregate -> {
            if (aggregate.id == null) aggregate.id = UUID.randomUUID().toString();
            return aggregate;
        };
    }

    /*
    @Bean
    NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getSchema() {
                return "catalog";
            }
        };
    }
     */
}
