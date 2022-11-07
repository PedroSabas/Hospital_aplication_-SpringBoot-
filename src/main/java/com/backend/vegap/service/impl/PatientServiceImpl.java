package com.backend.vegap.service.impl;

import com.backend.vegap.entity.Patient;
import com.backend.vegap.repository.IGenericRepository;
import com.backend.vegap.repository.PatientRepository;
import com.backend.vegap.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends CRUDServiceImpl<Patient, Integer> implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    /** abstract method from CRUDServiceImpl **/
    @Override
    protected IGenericRepository<Patient, Integer> getRepo() {
        return patientRepository;
    }
}
