package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.dto.Insurance;
import org.goafabric.catalogservice.service.persistence.InsuranceRepository;
import org.goafabric.catalogservice.service.persistence.bo.InsuranceBo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class InsuranceCatalogLogic implements CrudLogic<Insurance> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        Insurance map(InsuranceBo o);
        InsuranceBo map(Insurance o);
        List<Insurance> map(List<InsuranceBo> l);
    }

    private BoMapper mapper;
    private InsuranceRepository repository;

    public InsuranceCatalogLogic(BoMapper mapper, InsuranceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(Insurance Insurance) {
        repository.save(mapper.map(Insurance));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Insurance getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    @Override
    public List<Insurance> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }
}
