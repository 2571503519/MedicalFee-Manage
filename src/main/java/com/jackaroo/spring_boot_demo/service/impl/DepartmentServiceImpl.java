package com.jackaroo.spring_boot_demo.service.impl;

import com.jackaroo.spring_boot_demo.mapper.DepartmentMapper;
import com.jackaroo.spring_boot_demo.pojo.Department;
import com.jackaroo.spring_boot_demo.service.IDepartmentService;
import com.jackaroo.spring_boot_demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 14:08
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentsByStatus(Constant.CommonStatus status) {
        List<Department> departmentList =
                departmentMapper.getDepartmentsByStatus(status.getId());

        return departmentList;
    }
}
