package org.goafabric.catalogservice.service.repository;


import org.goafabric.catalogservice.service.repository.entity.ConditionEo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConditionRepository extends CrudRepository<ConditionEo, String> {
    List<ConditionEo> findByDisplayStartsWithIgnoreCase(String display);
}
