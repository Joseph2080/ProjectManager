package org.chitsa.projectmanagement.service;

import java.util.List;

public interface Service<T, T1> {
    void register(T1 t1);

    void update(Long id, T1 t1);

    T1 loadById(Long id);

    void delete(Long id);

    List<T1> loadAll();
}
