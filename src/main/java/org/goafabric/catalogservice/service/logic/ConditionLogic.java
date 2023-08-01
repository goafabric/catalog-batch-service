package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.logic.mapper.ConditionMapper;
import org.goafabric.catalogservice.service.repository.ConditionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ConditionLogic {

    private ConditionMapper mapper;
    private ConditionRepository repository;

    public ConditionLogic(ConditionMapper mapper, ConditionRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void create(Condition condition) {
        repository.save(mapper.map(condition));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Condition getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    public List<Condition> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }
}
