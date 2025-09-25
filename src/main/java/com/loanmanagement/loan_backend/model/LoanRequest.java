package com.loanmanagement.loan_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "loan_request")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment Req_Id
    @Column(name = "Req_Id")
    private Long reqId;

    @Column(name = "Loan_Id", nullable = false)
    private String loanId;

    @Column(name = "Emp_Id", nullable = false)
    private String empId;

    @Column(name = "Loan_Type", nullable = false)
    private String loanType;

    @Column(name = "Loan_Amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal loanAmount;

    @Column(name = "Months", nullable = false)
    private Integer months;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    // ✅ Getters & Setters
    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
