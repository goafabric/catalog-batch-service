package org.goafabric.catalogservice.service.logic.mapper;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.repository.entity.ConditionEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConditionMapper {
    Condition map(ConditionEo o);
    ConditionEo map(Condition o);
    List<Condition> map(List<ConditionEo> l);
}