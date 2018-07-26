package com.jackaroo.spring_boot_demo.mapper;

import com.jackaroo.spring_boot_demo.pojo.Department;
import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/7 13:22
 */
@Mapper
public interface DepartmentMapper {

    int saveDepartment(Department department);

    int updateDepartmentByPrimaryKeySelective(Department department);

    int deleteDepartmentByPrimaryKey(Long deptId);

    List<Department> getAllDepartment();

    List<Department> getDepartmentsByStatus(Integer status);

    Department getDepartmentByDeptId(Long deptId);

}
