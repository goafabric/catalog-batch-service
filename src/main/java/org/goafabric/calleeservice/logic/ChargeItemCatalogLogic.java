package org.goafabric.calleeservice.logic;

import org.goafabric.calleeservice.controller.dto.ChargeItem;
import org.goafabric.calleeservice.persistence.ChargeItemRepository;
import org.goafabric.calleeservice.persistence.bo.ChargeItemBo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
