package com.Projekat.repository;

import com.Projekat.model.EmployeeRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRegistrationRequest, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM employee_reg_request WHERE employee_reg_request.accepted=false AND employee_reg_request.rejected=false")
    List<EmployeeRegistrationRequest> getAllPendingRequests();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE employee_reg_request SET accepted=true WHERE acc_id=?1")
    void accept(Integer acc_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE employee_reg_request SET rejected=true WHERE acc_id=?1")
    void reject(Integer acc_id);
}
