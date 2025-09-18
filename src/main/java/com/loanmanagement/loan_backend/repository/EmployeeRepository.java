package com.loanmanagement.loan_backend.repository;

import com.loanmanagement.loan_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // JpaRepository already has findById() so no need to add custom query here
}


