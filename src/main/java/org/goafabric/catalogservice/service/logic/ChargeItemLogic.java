package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.ChargeItem;
import org.goafabric.catalogservice.service.logic.mapper.ChargeItemMapper;
import org.goafabric.catalogservice.service.repository.ChargeItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChargeItemLogic implements CrudLogic<ChargeItem> {

    private ChargeItemMapper mapper;
    private ChargeItemRepository repository;

    public ChargeItemLogic(ChargeItemMapper mapper, ChargeItemRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(ChargeItem ChargeItem) {
        repository.save(mapper.map(ChargeItem));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ChargeItem getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    @Override
    public List<ChargeItem> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }

}
