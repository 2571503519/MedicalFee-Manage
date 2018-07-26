package com.jackaroo.spring_boot_demo.vo;

import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import com.jackaroo.spring_boot_demo.util.Constant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JackarooZhang
 * @date 2018/6/10 21:01
 */
public class MedicalDetailVo {

    private Long medicalId;

    private String medicalNumber;

    private BigDecimal amount;

    private String desc;

    private Employee employee;

    private MedicalCategory medicalCategory;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Long getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Long medicalId) {
        this.medicalId = medicalId;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public MedicalCategory getMedicalCategory() {
        return medicalCategory;
    }

    public void setMedicalCategory(MedicalCategory medicalCategory) {
        this.medicalCategory = medicalCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
