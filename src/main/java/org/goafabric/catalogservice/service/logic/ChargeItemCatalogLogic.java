package org.goafabric.catalogservice.service.logic;

import org.goafabric.catalogservice.service.controller.dto.ChargeItem;
import org.goafabric.catalogservice.service.persistence.ChargeItemRepository;
import org.goafabric.catalogservice.service.persistence.bo.ChargeItemBo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class ChargeItemCatalogLogic implements CrudLogic<ChargeItem> {
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface BoMapper {
        ChargeItem map(ChargeItemBo o);
        ChargeItemBo map(ChargeItem o);
        List<ChargeItem> map(List<ChargeItemBo> l);
    }

    private BoMapper mapper;
    private ChargeItemRepository repository;

    public ChargeItemCatalogLogic(BoMapper mapper, ChargeItemRepository repository) {
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
