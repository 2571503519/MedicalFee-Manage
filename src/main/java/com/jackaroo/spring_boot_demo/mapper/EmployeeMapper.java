package com.jackaroo.spring_boot_demo.mapper;

import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/7 20:06
 */
@Mapper
public interface EmployeeMapper {

    int saveEmployee(Employee employee);

    int updateEmployeeByPrimaryKeySelective(Employee employee);

    int deleteEmployeeByPrimaryKey(Long empId);

    List<Employee> getAllEmployee(EmployeeQueryCondition queryCondition);

    Employee getEmployeeByEmpId(Long empId);

    Long getRowsCount();

}
