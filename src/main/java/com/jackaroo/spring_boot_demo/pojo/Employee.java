package com.jackaroo.spring_boot_demo.pojo;

import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * @author JackarooZhang
 * @date 2018/6/7 12:50
 */
@Alias("employee")
public class Employee {

    private Long empId;

    private String empNumber;

    private String name;

    private Constant.Gender gender;

    private String phone;

    private BigDecimal balance;

    private Department department;

    private Constant.EmployeeStatus status;

    private Long createTime;

    private Long updateTime;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constant.Gender getGender() {
        return gender;
    }

    public void setGender(Constant.Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Constant.EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.EmployeeStatus status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
