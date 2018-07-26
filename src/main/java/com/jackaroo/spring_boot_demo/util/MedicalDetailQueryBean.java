package com.jackaroo.spring_boot_demo.util;

/**
 * @author JackarooZhang
 * @date 2018/6/10 20:47
 */
public class MedicalDetailQueryBean {

    private String keywords;

    private Long medicalCateId;

    private Constant.CommonStatus status;

    public MedicalDetailQueryBean() {}

    public MedicalDetailQueryBean(String keywords, Long medicalCateId, Integer status) {
        this.keywords = keywords;
        this.medicalCateId = medicalCateId;
        if (status != null)
            this.status = Constant.CommonStatus.codeOf(status);
    }

    public Long getMedicalCateId() {
        return medicalCateId;
    }

    public void setMedicalCateId(Long medicalCateId) {
        this.medicalCateId = medicalCateId;
    }

    public Constant.CommonStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.CommonStatus status) {
        this.status = status;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
