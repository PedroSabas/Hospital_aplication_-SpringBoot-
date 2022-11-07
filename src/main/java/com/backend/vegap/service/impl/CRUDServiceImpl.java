package com.backend.vegap.service.impl;

import com.backend.vegap.repository.IGenericRepository;
import com.backend.vegap.service.CRUDService;

import java.util.List;

public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    /**
     * Recibe un metódo abstracto el cual recibe el repositorio de cada servicio
     * (MedicRepository, PatientRepository)
     * **/
    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }
}
