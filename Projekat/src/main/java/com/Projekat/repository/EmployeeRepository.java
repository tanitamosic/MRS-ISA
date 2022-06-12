package com.Projekat.repository;

import com.Projekat.model.EmployeeRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRegistrationRequest, Integer> {
}
