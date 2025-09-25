package com.loanmanagement.loan_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")

public class Payment {

    @Id
    @Column(name = "Payment_Id ")
    private String paymentId;

    @Column(name = "Emp_Id ")
    private String empId;

    @Column(name = "Ref_No ")
    private String refNo;

    @Column(name = "Effective_Date ")
    private String effectiveDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "Check_No ")
    private String checkNo;

    @Column(name = "Settled_Amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public String getEmpId() {
        return empId;
    }

    public String getRefNo() {
        return refNo;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
