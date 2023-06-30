package org.goafabric.catalogservice.service.repository;


import org.goafabric.catalogservice.service.repository.entity.DiagnosisEo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosisRepository extends CrudRepository<DiagnosisEo, String> {
    List<DiagnosisEo> findByDisplayStartsWithIgnoreCase(String display);
}
