package com.loanmanagement.loan_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
//.\mvnw.cmd spring-boot:run

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @Column(name = "Reference_No")
    private String refNo;

    @Column(name = "Loan_Id")
    private String loanId;

    @Column(name = "Emp_Id")
    private String empId;

    @Column(name = "Loan_Type")
    private String loanType;

    @Column(name = "Loan_Amount", precision = 20, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "Monthly_Installment", precision = 20, scale = 2)
    private BigDecimal instalment;

    @Column(name = "Paid_Amount", precision = 20, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "Remaining_Balance", precision = 20, scale = 2)
    private BigDecimal remainingBalance;

    @Column(name = "Loan_Date")
    private String loanDate;

    @Column(name = "Months")
    private int months;

    @Column(name = "Loan_Status")
    private String loanStatus; // e.g., Active / Closed

    @Column(name = "Payment_Status")
    private String paymentStatus; // e.g., Pending / Paid

    // Default constructor
    public Loan() {

    }

    // All arguments constructor
    public Loan(String refNo, String loanId, String empId, String loanType, BigDecimal loanAmount,
            BigDecimal instalment, BigDecimal paidAmount, BigDecimal remainingBalance, String loanDate, int months,
            String loanStatus, String paymentStatus) {

        this.refNo = refNo;
        this.loanId = loanId;
        this.empId = empId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.instalment = instalment;
        this.paidAmount = paidAmount;
        this.remainingBalance = remainingBalance;
        this.loanDate = loanDate;
        this.months = months;
        this.loanStatus = loanStatus;
        this.paymentStatus = paymentStatus;

    }

    // Getters and Setters

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
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

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public void setInstalment(BigDecimal instalment) {
        this.instalment = instalment;
    }

    public BigDecimal getInstalment() {
        return instalment;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
