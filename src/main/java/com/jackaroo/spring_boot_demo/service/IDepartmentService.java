package com.jackaroo.spring_boot_demo.service;

import com.jackaroo.spring_boot_demo.pojo.Department;
import com.jackaroo.spring_boot_demo.util.Constant;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 14:07
 */
public interface IDepartmentService {

    List<Department> getDepartmentsByStatus(Constant.CommonStatus status);

}
