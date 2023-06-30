package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.dto.Diagnosis;
import org.goafabric.catalogservice.service.crossfunctional.DurationLog;
import org.goafabric.catalogservice.service.persistence.DiagnosisRepository;
import org.goafabric.catalogservice.service.persistence.bo.DiagnosisBo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@DurationLog
@Transactional
public class DiagnosisCatalogLogic implements CrudLogic<Diagnosis> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        Diagnosis map(DiagnosisBo o);
        DiagnosisBo map(Diagnosis o);
        List<Diagnosis> map(List<DiagnosisBo> l);
    }

    private BoMapper mapper;
    private DiagnosisRepository repository;

    public DiagnosisCatalogLogic(BoMapper mapper, DiagnosisRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(Diagnosis diagnosis) {
        repository.save(mapper.map(diagnosis));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Diagnosis getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    @Override
    public List<Diagnosis> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }
}
