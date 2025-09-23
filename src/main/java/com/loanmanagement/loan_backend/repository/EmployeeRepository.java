package com.loanmanagement.loan_backend.repository;

import com.loanmanagement.loan_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; 

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
   boolean existsByEmpId(String empId);

   Optional<Employee> findByUsername(String username);
}
