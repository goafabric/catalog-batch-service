package org.goafabric.catalogservice.service.repository;

import org.goafabric.catalogservice.service.repository.entity.InsuranceEo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEo, String> {
    InsuranceEo getById(String id);

    List<InsuranceEo> findByDisplayStartsWithIgnoreCase(String display);
}
