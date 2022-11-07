package com.backend.vegap.service.impl;


import com.backend.vegap.entity.Medic;
import com.backend.vegap.repository.IGenericRepository;
import com.backend.vegap.repository.MedicRepository;
import com.backend.vegap.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicServiceImpl extends CRUDServiceImpl<Medic, Integer> implements MedicService {

    @Autowired
    private MedicRepository medicRepository;

    @Override
    protected IGenericRepository<Medic, Integer> getRepo() {
        return medicRepository;
    }
}
