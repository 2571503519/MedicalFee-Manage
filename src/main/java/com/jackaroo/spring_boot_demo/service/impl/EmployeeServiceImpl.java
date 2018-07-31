package com.jackaroo.spring_boot_demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.mapper.EmployeeMapper;
import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.service.IEmployeeService;
import com.jackaroo.spring_boot_demo.util.AjaxResponse;
import com.jackaroo.spring_boot_demo.util.Constant;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.util.page.PageConfig;
import com.jackaroo.spring_boot_demo.util.page.Pagination;
import com.jackaroo.spring_boot_demo.vo.EmployeeVo;
import com.jackaroo.spring_boot_demo.vo.MedicalDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 10:41
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Pagination<EmployeeVo> getEmployeeListConditionallyV2(EmployeeQueryCondition condition, PageQueryBean pageQueryBean) {
        PageHelper.startPage(pageQueryBean.getPageNumber(), pageQueryBean.getPageSize());
        List<Employee> allEmployee = employeeMapper.getAllEmployee(condition);
        List<EmployeeVo> employeeVoList = assembleEmployeeVo(allEmployee);

        String keywords = "";
        String deptId = "";
        String status = "";

        if (condition.getKeywords() != null) {
            keywords = condition.getKeywords();
        }
        if (condition.getDeptId() != null) {
            deptId = Long.toString(condition.getDeptId());
        }
        if (condition.getStatus() != null) {
            status = Integer.toString(condition.getStatus().getId());
        }

        String baseUrl = "/employeeList.html?keywords=" + keywords
                + "&dept_id=" + deptId + "&status=" + status;

        Long rowsCount = employeeMapper.getRowsCountConditionally(condition);
        PageConfig pageConfig = null;
        if (rowsCount < 1) {
            // （查询到符合条件的记录为0时）防止在设置当前页的时候，出现页码设置超出范围
            pageConfig = PageConfig.create(1L, 5, baseUrl, 5);
        } else {
            pageConfig = PageConfig.create(rowsCount, 5, baseUrl, 5);
        }
        Pagination<EmployeeVo> pagination = Pagination.create(pageConfig);
        pagination.setCurrentPageNumber(pageQueryBean.getPageNumber());
        pagination.setPageData(employeeVoList);

        return pagination;
    }

    @Override
    public PageInfo<EmployeeVo> getEmployeeListConditionally(EmployeeQueryCondition condition, PageQueryBean pageQueryBean) {
        PageHelper.startPage(pageQueryBean.getPageNumber(), pageQueryBean.getPageSize());
        List<Employee> employeeList = employeeMapper.getAllEmployee(condition);
        List<EmployeeVo> voList = assembleEmployeeVo(employeeList);
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(voList);
        assemblePageQueryBean(pageQueryBean);
        return pageInfo;
    }

    @Override
    public AjaxResponse<EmployeeVo> addEmployee(EmployeeVo employeeVo) {
        Employee employee = assembleEmployee(employeeVo);
        employee.setStatus(Constant.EmployeeStatus.NORMAL);
        int row = employeeMapper.saveEmployee(employee);
        if (row > 0) {
            employeeVo = assembleEmployeeVo(employeeMapper.getEmployeeByEmpId(employee.getEmpId()));
            return AjaxResponse.createBySuccess("新添职工信息成功:)", employeeVo);
        } else {
            return AjaxResponse.createByFail("新添职工信息失败!");
        }
    }


    @Override
    public EmployeeVo getEmployeeInfo(Employee employee) {
        Employee employeeQuery = employeeMapper.getEmployeeByEmpId(employee.getEmpId());
        EmployeeVo employeeVo = null;
        if (employeeQuery != null) {
            employeeVo = assembleEmployeeVo(employeeQuery);
        }
        return employeeVo;
    }

    @Override
    public AjaxResponse deleteEmployee(EmployeeVo employeeVo) {
        if (employeeVo != null && employeeVo.getEmpId() != null) {
            Employee employee = employeeMapper.getEmployeeByEmpId(employeeVo.getEmpId());
            employee.setStatus(Constant.EmployeeStatus.DELETE);
            int row = employeeMapper.updateEmployeeByPrimaryKeySelective(employee);
            if (row > 0) {
                return AjaxResponse.createBySuccess("删除成功");
            } else {
                return AjaxResponse.createByFail("删除失败");
            }
        } else {
            return AjaxResponse.createByFail("删除失败");
        }
    }

    @Override
    public AjaxResponse deleteEmployees(List<EmployeeVo> employeeVoList) {
        return AjaxResponse.createBySuccess("删除成功！");
    }

    @Override
    public AjaxResponse<EmployeeVo> updateEmployInfo(EmployeeVo employeeVo) {
        if (employeeVo == null || employeeVo.getEmpId() == null) {
            return AjaxResponse.createByFail("更新职工信息失败！");
        } else {
            Employee employee = assembleEmployee(employeeVo);
            int row = employeeMapper.updateEmployeeByPrimaryKeySelective(employee);
            if (row > 0) {
                return AjaxResponse.createBySuccess("更新职工信息成功:)");
            } else {
                return AjaxResponse.createBySuccess("更新职工信息失败！");
            }
        }
    }


    private void assemblePageQueryBean(PageQueryBean pageQueryBean) {
        Long rowsCount = employeeMapper.getRowsCount();
        pageQueryBean.setTotal(rowsCount);
    }

    private Employee assembleEmployee(EmployeeVo vo) {
        Employee employee = new Employee();

        employee.setEmpId(vo.getEmpId());
        employee.setEmpNumber(vo.getEmpNumber());
        employee.setName(vo.getName());
        employee.setGender(Constant.Gender.getGender(vo.getGender()));
        employee.setPhone(vo.getPhone());
        employee.setBalance(vo.getBalance());
        employee.setStatus(Constant.EmployeeStatus.getStatus(vo.getStatus()));
        employee.setDepartment(vo.getDepartment());

        return employee;
    }


    private List<EmployeeVo> assembleEmployeeVo(List<Employee> list) {
        List<EmployeeVo> voList = new ArrayList<>();
        EmployeeVo vo = null;
        for (Employee e : list) {
            vo = new EmployeeVo();
            vo.setEmpId(e.getEmpId());
            vo.setName(e.getName());
            vo.setBalance(e.getBalance());
            vo.setEmpNumber(e.getEmpNumber());
            vo.setPhone(e.getPhone());
            vo.setGender(e.getGender().getName());
            vo.setDepartment(e.getDepartment());
            vo.setStatus(e.getStatus().getStatus());
            vo.setCreateTime(new Date(e.getCreateTime()));
            vo.setUpdateTime(new Date(e.getUpdateTime()));
            voList.add(vo);
        }
        return voList;
    }

    private EmployeeVo assembleEmployeeVo(Employee e) {
        EmployeeVo vo = new EmployeeVo();
        vo.setEmpId(e.getEmpId());
        vo.setName(e.getName());
        vo.setBalance(e.getBalance());
        vo.setEmpNumber(e.getEmpNumber());
        vo.setPhone(e.getPhone());
        vo.setGender(e.getGender().getName());
        vo.setDepartment(e.getDepartment());
        vo.setStatus(e.getStatus().getStatus());
        vo.setCreateTime(new Date(e.getCreateTime()));
        vo.setUpdateTime(new Date(e.getUpdateTime()));
        return vo;
    }
}
