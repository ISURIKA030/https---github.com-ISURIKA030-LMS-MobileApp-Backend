package com.loanmanagement.loan_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
//.\mvnw.cmd spring-boot:run

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @Column(name = "Reference_No")
    private String RefNo;

    @Column(name = "Loan_Id")
    private String LoanId;

    @Column(name = "Emp_Id")
    private String empId;

    @Column(name = "Loan_Type")
    private String loanType;

    @Column(name = "Loan_Amount", precision = 20, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "Monthly_Installment", precision = 20, scale = 2)
    private BigDecimal Instalment;

    @Column(name = "Paid_Amount", precision = 20, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "Remaining_Balance", precision = 20, scale = 2)
    private BigDecimal remainingBalance;

    @Column(name = "Loan_Date")
    private String LoanDate;

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
    public Loan(String RefNo, String LoanId, String empId, String loanType, BigDecimal loanAmount,
            BigDecimal Instalment, BigDecimal paidAmount, BigDecimal remainingBalance, String LoanDate, int months,
            String loanStatus, String paymentStatus) {

        this.RefNo = RefNo;
        this.LoanId = LoanId;
        this.empId = empId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.Instalment = Instalment;
        this.paidAmount = paidAmount;
        this.remainingBalance = remainingBalance;
        this.LoanDate = LoanDate;
        this.months = months;
        this.loanStatus = loanStatus;
        this.paymentStatus = paymentStatus;

    }

    // Getters and Setters

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public String getLoanId() {
        return RefNo;
    }

    public void setLoanId(String LoanId) {
        this.LoanId = LoanId;
    }

    public String getempId() {
        return empId;
    }

    public void setempId(String empId) {
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
        return LoanDate;
    }

    public void setLoanDate(String LoanDate) {
        this.LoanDate = LoanDate;
    }

    public void setInstalment(BigDecimal Instalment) {
        this.Instalment = Instalment;
    }

    public BigDecimal getInstalment() {
        return Instalment;
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
