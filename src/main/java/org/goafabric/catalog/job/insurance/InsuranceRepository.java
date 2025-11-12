package org.goafabric.catalog.job.insurance;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEo, String> {
    @Query("SELECT * FROM catalog.insurance WHERE code = :code")
    List<InsuranceEo> findByCode(String code);

    @Query("SELECT * FROM catalog.insurance WHERE UPPER(display) LIKE CONCAT(UPPER(:display), '%')")
    List<InsuranceEo> findByDisplayStartsWith(String display);
}
