package org.goafabric.catalogservice.persistence;


import org.goafabric.catalogservice.persistence.bo.DiagnosisBo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosisRepository extends CrudRepository<DiagnosisBo, String> {
    List<DiagnosisBo> findByDisplayStartsWithIgnoreCase(String display);
}
