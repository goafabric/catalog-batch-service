package org.goafabric.catalogservice.service.persistence;

import org.goafabric.catalogservice.service.persistence.bo.ChargeItemBo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChargeItemRepository extends CrudRepository<ChargeItemBo, String> {
    List<ChargeItemBo> findByDisplayStartsWithIgnoreCase(String display);
}
