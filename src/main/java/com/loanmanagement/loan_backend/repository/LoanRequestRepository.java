package com.loanmanagement.loan_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loanmanagement.loan_backend.model.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    // No need to define save(), it's inherited from JpaRepository
}
