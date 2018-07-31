package com.jackaroo.spring_boot_demo.web.controller;

import com.jackaroo.spring_boot_demo.service.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JackarooZhang
 * @date 2018/7/27 8:56
 */
@Controller
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private IDepartmentService iDepartmentService;

    @RequestMapping("/departmentList.html")
    public ModelAndView deptList() {
        ModelAndView mv = new ModelAndView("index/departmentList");


        return mv;
    }

}
