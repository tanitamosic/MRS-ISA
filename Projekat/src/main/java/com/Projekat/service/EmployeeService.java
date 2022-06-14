package com.Projekat.service;

import com.Projekat.model.EmployeeRegistrationRequest;
import com.Projekat.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(EmployeeRegistrationRequest request) { employeeRepository.save(request);}

    public List<EmployeeRegistrationRequest> getAllPendingRequests() { return employeeRepository.getAllPendingRequests(); }

    public void accept(Integer acc_id) { employeeRepository.accept(acc_id); }

    public void reject(Integer acc_id) { employeeRepository.reject(acc_id); }
}
