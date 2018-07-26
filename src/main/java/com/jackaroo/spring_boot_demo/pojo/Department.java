package com.jackaroo.spring_boot_demo.pojo;

import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.type.Alias;

/**
 * @author JackarooZhang
 * @date 2018/6/7 13:11
 */
@Alias("department")
public class Department {

    private Long deptId;

    private String deptName;

    private String deptDesc;

    private Constant.CommonStatus deptStatus;

    private Long createTime;

    private Long updateTime;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public Constant.CommonStatus getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(Constant.CommonStatus deptStatus) {
        this.deptStatus = deptStatus;
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
