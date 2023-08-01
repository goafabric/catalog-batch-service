package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.vo.ChargeItem;
import org.goafabric.catalogservice.service.repository.ChargeItemRepository;
import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChargeItemLogic implements CrudLogic<ChargeItem> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        ChargeItem map(ChargeItemEo o);
        ChargeItemEo map(ChargeItem o);
        List<ChargeItem> map(List<ChargeItemEo> l);
    }

    private BoMapper mapper;
    private ChargeItemRepository repository;

    public ChargeItemLogic(BoMapper mapper, ChargeItemRepository repository) {
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
