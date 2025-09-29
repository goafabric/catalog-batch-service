package org.goafabric.catalog.job.condition;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConditionRepository extends CrudRepository<ConditionEo, String> {
    @Query("SELECT * FROM catalog.condition WHERE code = :code")
    ConditionEo findByCode(String code);

    @Query("SELECT * FROM catalog.condition WHERE UPPER(display) LIKE CONCAT(UPPER(:display), '%')")
    List<ConditionEo> findByDisplayStartsWithIgnoreCase(String display);
}
