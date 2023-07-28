package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.Diagnosis;
import org.goafabric.catalogservice.service.repository.DiagnosisRepository;
import org.goafabric.catalogservice.service.repository.entity.DiagnosisEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DiagnosisCatalogLogic implements CrudLogic<Diagnosis> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        Diagnosis map(DiagnosisEo o);
        DiagnosisEo map(Diagnosis o);
        List<Diagnosis> map(List<DiagnosisEo> l);
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
