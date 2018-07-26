package com.jackaroo.spring_boot_demo.web.controller;

import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.pojo.Department;
import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.service.IDepartmentService;
import com.jackaroo.spring_boot_demo.service.IEmployeeService;
import com.jackaroo.spring_boot_demo.util.AjaxResponse;
import com.jackaroo.spring_boot_demo.util.Constant;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.vo.EmployeeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 9:56
 */
@Controller
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IDepartmentService iDepartmentService;


    @RequestMapping("/employeeAdd.html")
    public ModelAndView employeeAdd(@RequestParam(value = "emp_id", required = false) Long empId) {
        ModelAndView mv = new ModelAndView("index/employeeAdd");

        EmployeeVo employeeVo = null;
        if (empId != null) {
            // 修改操作
            Employee employee = new Employee();
            employee.setEmpId(empId);
            employeeVo = iEmployeeService.getEmployeeInfo(employee);
        } else {
            // 新增操作
        }
        // 查询所有正常状态的部门
        List<Department> departmentList = iDepartmentService.getDepartmentsByStatus(Constant.CommonStatus.NORMAL);

        mv.addObject("departmentList", departmentList);
        mv.addObject("employeeInfo", employeeVo);
        return mv;
    }

    @RequestMapping(value = "/addEmployee.do", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<EmployeeVo> employeeAdd(EmployeeVo employeeVo) {

        return iEmployeeService.addEmployee(employeeVo);
    }

    @RequestMapping(value = "/updateEmployeeInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<EmployeeVo> updateEmployeeInfo(EmployeeVo employeeVo) {
        return iEmployeeService.updateEmployInfo(employeeVo);
    }


    @RequestMapping(value = "/delEmployee.do", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse employeeDelete(@RequestParam(value = "empIds", required = true, defaultValue = "") Long[] empIds) {
        logger.info("需要删除的Ids：{}", Arrays.toString(empIds));
        if (empIds.length == 1) {
            EmployeeVo employeeVo = new EmployeeVo();
            employeeVo.setEmpId(empIds[0]);
            return iEmployeeService.deleteEmployee(employeeVo);
        } else {
            return iEmployeeService.deleteEmployees(null);
        }
    }

    @RequestMapping("/employeeList.html")
    public ModelAndView employeeList(@RequestParam(value = "keywords", required = false) String keywords
            , @RequestParam(value = "dept_id", required = false) Long deptId
            , @RequestParam(value = "status", required = false) Integer status
            , @RequestParam(value = "page", defaultValue = "1") Integer pageNumber
            , @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        ModelAndView mv = new ModelAndView("index/employeeList");

        PageQueryBean pageQueryBean = new PageQueryBean(pageNumber, pageSize);
        EmployeeQueryCondition queryCondition = new EmployeeQueryCondition(keywords, deptId, status);
        // 分页查询符合查询条件的所有职工
        PageInfo<EmployeeVo> pageInfo = iEmployeeService.getEmployeeListConditionally(queryCondition, pageQueryBean);
        // 查询所有正常状态的部门
        List<Department> departmentList = iDepartmentService.getDepartmentsByStatus(Constant.CommonStatus.NORMAL);

        mv.addObject("employeeVoList", pageInfo.getList());
        mv.addObject("departmentList", departmentList);
        mv.addObject("statusList", Constant.EmployeeStatus.values());
        mv.addObject("keywords", keywords);
        mv.addObject("deptId", deptId);
        mv.addObject("status", status);
        mv.addObject("pageInfo", pageQueryBean);
        return mv;
    }

}
