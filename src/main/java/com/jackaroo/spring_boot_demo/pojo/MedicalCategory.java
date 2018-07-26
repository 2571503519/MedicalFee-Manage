package com.jackaroo.spring_boot_demo.pojo;

import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.type.Alias;

/**
 * @author JackarooZhang
 * @date 2018/6/8 8:56
 */
@Alias("medicalCategory")
public class MedicalCategory {

    private Long medicalCateId;

    private String cateName;

    private String cateDesc;

    private Constant.CommonStatus status;

    private Long createTime;

    private Long updateTime;


    public Long getMedicalCateId() {
        return medicalCateId;
    }

    public void setMedicalCateId(Long medicalCateId) {
        this.medicalCateId = medicalCateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
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
