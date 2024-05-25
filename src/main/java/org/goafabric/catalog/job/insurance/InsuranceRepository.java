package org.goafabric.catalog.job.insurance;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEo, String> {
    List<InsuranceEo> findByCode(String code);
    List<InsuranceEo> findByDisplayStartsWithIgnoreCase(String display);
}
