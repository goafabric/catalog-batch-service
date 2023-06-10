package org.goafabric.calleeservice.persistence;


import org.goafabric.calleeservice.persistence.bo.DiagnosisBo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosisRepository extends CrudRepository<DiagnosisBo, String> {
    List<DiagnosisBo> findByDisplayStartsWithIgnoreCase(String display);
}
