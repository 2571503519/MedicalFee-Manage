package com.jackaroo.spring_boot_demo;

import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.service.IEmployeeService;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.vo.EmployeeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTests {

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImplTests.class);

    @Autowired
    private IEmployeeService iEmployeeService;

    @Test
    public void testEmployeeServiceImplSelect() {
        EmployeeQueryCondition condition = new EmployeeQueryCondition();
        PageQueryBean pageQueryBean = new PageQueryBean();
        pageQueryBean.setPageSize(1);
        condition.setKeywords("张家乐");
        PageInfo<EmployeeVo> pageInfo = iEmployeeService.getEmployeeListConditionally(condition, pageQueryBean);
        List<EmployeeVo> employeeList = pageInfo.getList();
        logger.info("\n总页数：{}\n", pageInfo.getPages());
        employeeList.stream().forEach(e -> {
            logger.info("第一页---------------");
            logger.info("姓名：{}\n部门：{}\n性别：{}\n状态：{}\n\n", e.getName(), e.getDepartment().getDeptName()
                    , e.getGender(), e.getStatus());
        });
        pageQueryBean.setPageNumber(pageInfo.getNextPage());
        pageInfo = iEmployeeService.getEmployeeListConditionally(condition, pageQueryBean);
        employeeList = pageInfo.getList();
        employeeList.stream().forEach(e -> {
            logger.info("下一页---------------");
            logger.info("姓名：{}\n部门：{}\n性别：{}\n状态：{}\n\n", e.getName(), e.getDepartment().getDeptName()
                    , e.getGender(), e.getStatus());
        });
    }

}
