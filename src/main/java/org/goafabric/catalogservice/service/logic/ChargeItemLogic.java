package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.ChargeItem;
import org.goafabric.catalogservice.service.logic.mapper.ChargeItemMapper;
import org.goafabric.catalogservice.service.repository.ChargeItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChargeItemLogic {

    private ChargeItemMapper mapper;
    private ChargeItemRepository repository;

    public ChargeItemLogic(ChargeItemMapper mapper, ChargeItemRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void create(ChargeItem ChargeItem) {
        repository.save(mapper.map(ChargeItem));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public ChargeItem getById(String id) {
        return mapper.map(repository.findById(id).get());
    }

    public List<ChargeItem> search(String search) {
        return mapper.map(repository.findByDisplayStartsWithIgnoreCase(search));
    }

}
