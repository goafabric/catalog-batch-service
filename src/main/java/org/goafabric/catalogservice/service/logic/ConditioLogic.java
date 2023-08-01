package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.logic.mapper.ConditionMapper;
import org.goafabric.catalogservice.service.repository.ConditionRepository;
import org.goafabric.catalogservice.service.repository.entity.ConditionEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ConditioLogic implements CrudLogic<Condition> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        Condition map(ConditionEo o);
        ConditionEo map(Condition o);
        List<Condition> map(List<ConditionEo> l);
    }

    private ConditionMapper mapper;
    private ConditionRepository repository;

    public ConditioLogic(ConditionMapper mapper, ConditionRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(Condition condition) {
        repository.save(mapper.map(condition));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Condition getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    @Override
    public List<Condition> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }
}
