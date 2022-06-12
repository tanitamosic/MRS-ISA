package com.Projekat.service;

import com.Projekat.model.EmployeeRegistrationRequest;
import com.Projekat.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(EmployeeRegistrationRequest request) { employeeRepository.save(request);}
}
