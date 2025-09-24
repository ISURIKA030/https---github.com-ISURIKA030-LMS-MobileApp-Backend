package com.loanmanagement.loan_backend.repository;

import com.loanmanagement.loan_backend.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    // Fetch loans by employee and status
    List<Loan> findByEmpIdAndLoanStatusIn(String empId, List<String> loanStatuses);
}
