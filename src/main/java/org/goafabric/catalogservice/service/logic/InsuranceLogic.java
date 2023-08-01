package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.Insurance;
import org.goafabric.catalogservice.service.logic.mapper.InsuranceMapper;
import org.goafabric.catalogservice.service.repository.InsuranceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class InsuranceLogic {

    private InsuranceMapper mapper;
    private InsuranceRepository repository;

    public InsuranceLogic(InsuranceMapper mapper, InsuranceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void create(Insurance Insurance) {
        repository.save(mapper.map(Insurance));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Insurance getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    public List<Insurance> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }
}
