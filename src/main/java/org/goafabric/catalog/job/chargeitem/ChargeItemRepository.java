package org.goafabric.catalog.job.chargeitem;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChargeItemRepository extends CrudRepository<ChargeItemEo, String> {
    @Query("SELECT * FROM catalog.chargeitem WHERE code = :code")
    ChargeItemEo findByCode(String code);

    @Query("SELECT * FROM catalog.chargeitem WHERE UPPER(display) LIKE CONCAT(UPPER(:display), '%')")
    List<ChargeItemEo> findByDisplayStartsWithIgnoreCase(String display);
}
