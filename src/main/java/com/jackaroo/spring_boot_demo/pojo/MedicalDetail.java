package com.jackaroo.spring_boot_demo.pojo;

import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * @author JackarooZhang
 * @date 2018/6/8 9:19
 */
@Alias("medicalDetail")
public class MedicalDetail {

    private Long medicalId;

    private String medicalNumber;

    private BigDecimal amount;

    private String desc;

    private Employee employee;

    private MedicalCategory medicalCategory;

    private Constant.CommonStatus status;

    private Long createTime;

    private Long updateTime;

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

    public Constant.CommonStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.CommonStatus status) {
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
