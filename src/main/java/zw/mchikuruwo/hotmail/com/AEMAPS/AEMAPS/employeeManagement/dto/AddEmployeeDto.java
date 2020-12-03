package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.enums.Gender;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.enums.ResidentialStatus;

import java.math.BigDecimal;

public class AddEmployeeDto {
    private String employeeCode;
    private String name;
    private String surname;
    private Gender gender;
    private String mobileNumber;
    private ResidentialStatus residentialStatus;
    private String address1;
    private Double monthlySalary;
    private String accountNumber;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ResidentialStatus getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(ResidentialStatus residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
