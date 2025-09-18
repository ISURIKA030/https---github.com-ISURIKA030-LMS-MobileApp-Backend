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


}
