package org.goafabric.catalog.job.condition;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConditionRepository extends CrudRepository<ConditionEo, String> {
    ConditionEo findByCode(String code);
    List<ConditionEo> findByDisplayStartsWithIgnoreCase(String display);
}
