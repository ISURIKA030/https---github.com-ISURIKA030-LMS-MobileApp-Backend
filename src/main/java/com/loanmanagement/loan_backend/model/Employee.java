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

    //Default constructor
    public  Employee(){

    }
    //All arguments constructor
    public Employee(String empId, String empName, String department, String contactNo, String address, 
    String salaryType, String salary, String availability, String location, byte[] empPhoto ){

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

    }

    //getters and setters

    public String getempId() {
        return empId;
    }

    public void setempId(String empId) {
        this.empId = empId;
    }

    public String getempName() {
        return empId;
    }

    public void setempName(String empName) {
        this.empName = empName;
    }

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public String getcontactNo() {
        return contactNo;
    }

    public void setcontactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getsalaryType() {
        return salaryType;
    }

    public void setsalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getsalary() {
        return salary;
    }

    public void setsalary(String salary) {
        this.salary = salary;
    }

    public String getavailability() {
        return availability;
    }

    public void availability(String availability) {
        this.availability = availability;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }

    public byte[] getempPhoto() {
        return empPhoto;
    }

    public void setempPhoto(byte[] empPhoto) {
        this.empPhoto = empPhoto;
    }


}
