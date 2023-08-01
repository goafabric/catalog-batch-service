package org.goafabric.catalogservice.service.repository;

import org.goafabric.catalogservice.service.repository.entity.InsuranceEo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEo, String> {
    List<InsuranceEo> findByDisplayStartsWithIgnoreCase(String display);
}
