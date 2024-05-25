package org.goafabric.catalog.job.chargeitem;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChargeItemRepository extends CrudRepository<ChargeItemEo, String> {
    ChargeItemEo findByCode(String code);
    List<ChargeItemEo> findByDisplayStartsWithIgnoreCase(String display);
}
