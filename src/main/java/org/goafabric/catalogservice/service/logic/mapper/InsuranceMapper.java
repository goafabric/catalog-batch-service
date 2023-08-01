package org.goafabric.catalogservice.service.logic.mapper;

import org.goafabric.catalogservice.service.controller.vo.Insurance;
import org.goafabric.catalogservice.service.repository.entity.InsuranceEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsuranceMapper {
    Insurance map(InsuranceEo o);
    InsuranceEo map(Insurance o);
    List<Insurance> map(List<InsuranceEo> l);
}