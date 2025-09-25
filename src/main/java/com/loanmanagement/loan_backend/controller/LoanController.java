package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Loan;
import com.loanmanagement.loan_backend.repository.LoanRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    /** Get logged-in employee's Active and New loans */
    @GetMapping("/my-loans")
    public ResponseEntity<?> getMyLoans(HttpServletRequest request) {

        // Read empId from request attribute set by JwtFilter
        String empId = (String) request.getAttribute("empId");

        if (empId == null) {
            return ResponseEntity.status(401).body("Unauthorized: empId not found in token.");
        }

        // Fetch loans with status Active or New
        List<Loan> loans = loanRepository.findByEmpIdAndLoanStatusIn(empId, Arrays.asList("Active", "New"));

        if (loans.isEmpty()) {
            return ResponseEntity.ok("No Active or New loans found for employee: " + empId);
        }

        return ResponseEntity.ok(loans);
    }

    /** Get logged-in employee's loans with Payment_Status = Pending */
    @GetMapping("/my-pending-loans")
    public ResponseEntity<?> getMyPendingLoans(HttpServletRequest request) {
        String empId = (String) request.getAttribute("empId");

        if (empId == null) {
            return ResponseEntity.status(401).body("Unauthorized: empId not found in token.");
        }

        List<Loan> pendingLoans = loanRepository.findByEmpIdAndPaymentStatus(empId, "Pending");

        if (pendingLoans.isEmpty()) {
            return ResponseEntity.ok("No pending loans found for employee: " + empId);
        }

        return ResponseEntity.ok(pendingLoans);
    }
}
