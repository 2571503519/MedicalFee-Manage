package com.jackaroo.spring_boot_demo.util;

/**
 * @author JackarooZhang
 * @date 2018/6/8 10:43
 */
public class EmployeeQueryCondition {

    private String keywords;

    private Long deptId;

    private Constant.EmployeeStatus status;

    public EmployeeQueryCondition() {}

    public EmployeeQueryCondition(String keywords, Long deptId, Integer status) {
        this.keywords = keywords;
        this.deptId = deptId;
        if (status != null)
            this.status = Constant.EmployeeStatus.codeOf(status);
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Constant.EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.EmployeeStatus status) {
        this.status = status;
    }
}
