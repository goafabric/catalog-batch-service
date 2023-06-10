package org.goafabric.catalogservice.service.logic;

import java.util.List;

public interface CrudLogic<T> {

    void create(T t);

    void delete(String id);
    void deleteAll();

    T getById(String id);

    List<T> search(String search);
}
