package org.goafabric.catalogservice.service.repository;

import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChargeItemRepository extends CrudRepository<ChargeItemEo, String> {
    List<ChargeItemEo> findByDisplayStartsWithIgnoreCase(String display);
}
