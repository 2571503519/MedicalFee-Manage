package com.jackaroo.spring_boot_demo.service;

import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.util.AjaxResponse;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.util.page.Pagination;
import com.jackaroo.spring_boot_demo.vo.EmployeeVo;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 10:41
 */
public interface IEmployeeService {

    Pagination<EmployeeVo> getEmployeeListConditionallyV2(EmployeeQueryCondition condition, PageQueryBean pageQueryBean);

    PageInfo<EmployeeVo> getEmployeeListConditionally(EmployeeQueryCondition condition, PageQueryBean pageQueryBean);

    AjaxResponse<EmployeeVo> addEmployee(EmployeeVo employeeVo);

    EmployeeVo getEmployeeInfo(Employee employee);

    AjaxResponse deleteEmployee(EmployeeVo employeeVo);

    AjaxResponse deleteEmployees(List<EmployeeVo> employeeVoList);

    AjaxResponse<EmployeeVo> updateEmployInfo(EmployeeVo employeeVo);
}
