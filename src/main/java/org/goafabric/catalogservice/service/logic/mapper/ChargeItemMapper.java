package org.goafabric.catalogservice.service.logic.mapper;

import org.goafabric.catalogservice.service.controller.vo.ChargeItem;
import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChargeItemMapper {
    ChargeItem map(ChargeItemEo o);
    ChargeItemEo map(ChargeItem o);
    List<ChargeItem> map(List<ChargeItemEo> l);
}