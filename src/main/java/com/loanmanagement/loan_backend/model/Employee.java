package com.loanmanagement.loan_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "Emp_Id")
    private String empId;

    @Column(name = "Emp_Name")
    private String empName;

    @Column(name = "Department")
    private String department;

    @Column(name = "ContactNo")
    private String contactNo;

    @Column(name = "Address")
    private String address;

    @Column(name = "Salary_Type")
    private String salaryType;

    @Column(name = "Gross_Salary")
    private String salary;

    @Column(name = "Availability")
    private String availability;

    @Column(name = "Location")
    private String location;

    @Column(name = "EmpPhoto", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] empPhoto;

    @Column(name = "username")
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    // Default constructor
    public Employee() {

    }

    // All arguments constructor
    public Employee(String empId, String empName, String department, String contactNo, String address,
            String salaryType, String salary, String availability, String location, byte[] empPhoto, String username,
            String password) {

        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.contactNo = contactNo;
        this.address = address;
        this.salaryType = salaryType;
        this.salary = salary;
        this.availability = availability;
        this.location = location;
        this.empPhoto = empPhoto;
        this.username = username;
        this.password = password;

    }

    // getters and setters

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(byte[] empPhoto) {
        this.empPhoto = empPhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
