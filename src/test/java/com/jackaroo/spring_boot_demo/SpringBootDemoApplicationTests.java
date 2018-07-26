package com.jackaroo.spring_boot_demo;

import com.jackaroo.spring_boot_demo.mapper.DepartmentMapper;
import com.jackaroo.spring_boot_demo.mapper.EmployeeMapper;
import com.jackaroo.spring_boot_demo.mapper.MedicalCategoryMapper;
import com.jackaroo.spring_boot_demo.mapper.MedicalDetailMapper;
import com.jackaroo.spring_boot_demo.pojo.Department;
import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import com.jackaroo.spring_boot_demo.pojo.MedicalDetail;
import com.jackaroo.spring_boot_demo.util.Constant;
import com.jackaroo.spring_boot_demo.util.EmployeeQueryCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private MedicalCategoryMapper medicalCategoryMapper;

	@Autowired
	private MedicalDetailMapper medicalDetailMapper;

	private static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMedicalDetailMapperAdd() {
		for (int i = 0; i < 50; i++) {
			Employee employee = employeeMapper.getEmployeeByEmpId(3L);
			MedicalCategory category = medicalCategoryMapper.getMedicalCategoryByMedicalCateId(2L);
			if (employee != null && category != null) {
				MedicalDetail detail = new MedicalDetail();
				detail.setMedicalNumber(UUID.randomUUID().toString().replace("-", "").substring(24));
				detail.setAmount(new BigDecimal(100));
				detail.setDesc("一段描述信息2...");
				detail.setStatus(Constant.CommonStatus.NORMAL);
				detail.setEmployee(employee);
				detail.setMedicalCategory(category);
				int row = medicalDetailMapper.saveMedicalDetail(detail);
				logger.info("受影响行数：{}", row);
			}
		}
	}

	@Test
	public void testMedicalDetailMapperUpdate() {
		MedicalDetail detail = medicalDetailMapper.getMedicalDetailByEmpId(1L);
		if (detail != null) {
			detail.setDesc("另一段描述信息...");
			detail.setStatus(Constant.CommonStatus.FORBID);
			int row = medicalDetailMapper.updateMedicalDetailByPrimaryKeySelective(detail);
			logger.info("受影响行数：{}", row);
		}
	}

	@Test
	public void testMedicalDetailMapperSelect() {
		List<MedicalDetail> allDetail = medicalDetailMapper.getAllMedicalDetail(null);
		allDetail.stream().forEach(e -> {
			logger.info("\n单号：{}\n金额：{}\n描述：{}\n职工：{}\n类别：{}\n状态：{}\n更新时间：{}\n\n", e.getMedicalNumber()
					, e.getAmount(), e.getDesc(), e.getEmployee().getName(), e.getMedicalCategory().getCateName()
					, e.getStatus().getStatus(), new Date(e.getUpdateTime()));
		});
	}

	@Test
	public void testMedicalDetailMapperDelete() {
		MedicalDetail detail = medicalDetailMapper.getMedicalDetailByEmpId(1L);
		if (detail != null) {
			int row = medicalDetailMapper.deleteMedicalDetailByPrimaryKey(detail.getMedicalId());
			logger.info("受影响行数：{}", row);
		}
	}


	@Test
	public void testMedicalCategoryMapperAdd() {
		MedicalCategory category = new MedicalCategory();
		category.setCateName("校内门诊");
		category.setCateDesc("校内门诊...");
		category.setStatus(Constant.CommonStatus.NORMAL);
		int row = medicalCategoryMapper.saveMedicalCategory(category);
		logger.info("受影响行数：{}", row);
	}

	@Test
	public void testMedicalCategoryMapperUpdate() {
		MedicalCategory category = medicalCategoryMapper.getMedicalCategoryByMedicalCateId(1L);
		if (category != null) {
			category.setCateName("子女医疗费");
			int row = medicalCategoryMapper.updateMedicalCategoryByPrimaryKeySelective(category);
			logger.info("受影响行数：{}", row);
		}
	}

	@Test
	public void testMedicalCategoryMapperSelect() {
		List<MedicalCategory> allCategory = medicalCategoryMapper.getAllMedicalCategory();
		allCategory.stream().forEach(e -> {
			logger.info("\n名称：{}\n状态：{}\n更新时间：{}\n\n", e.getCateName(), e.getStatus().getStatus()
					, new Date(e.getUpdateTime()));
		});
	}

	@Test
	public void testMedicalCategoryMapperDelete() {
		MedicalCategory category = medicalCategoryMapper.getMedicalCategoryByMedicalCateId(1L);
		if (category != null) {
			int row = medicalCategoryMapper.deleteMedicalCategoryByPrimaryKey(category.getMedicalCateId());
			logger.info("受影响行数：{}", row);
		}
	}

	@Test
	public void testEmployeeMapperAdd() {
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee();
			Department department = departmentMapper.getDepartmentByDeptId(4L);
			if (department != null) {
				employee.setDepartment(department);
				employee.setEmpNumber(UUID.randomUUID().toString().replace("-", "").substring(20));
				employee.setName("曾林" + i);
				employee.setPhone("15217949347");
				employee.setBalance(new BigDecimal(800));
				employee.setGender(Constant.Gender.FEMALE);
				employee.setStatus(Constant.EmployeeStatus.DELETE);
				int row = employeeMapper.saveEmployee(employee);
				logger.info("受影响行数：{}", row);
			}
		}
	}

	@Test
	public void testEmployeeMapperUpdate() {
		Employee employee = employeeMapper.getEmployeeByEmpId(1L);
		if (employee != null) {
			employee.setStatus(Constant.EmployeeStatus.DELETE);
			employee.setGender(Constant.Gender.FEMALE);
			employee.setName("黄光正");
			int row = employeeMapper.updateEmployeeByPrimaryKeySelective(employee);
			logger.info("受影响行数：{}", row);
		}
	}

	@Test
	public void testEmployeeMapperSelect() {
		EmployeeQueryCondition condition = new EmployeeQueryCondition();
		condition.setKeywords("张家乐");
		condition.setDeptId(2L);
//		condition.setStatus(Constant.EmployeeStatus.FORBID);
		List<Employee> allEmployee = employeeMapper.getAllEmployee(condition);
		allEmployee.stream().forEach(e -> {
			logger.info("姓名：{}\n性别：{}\n状态：{}\n\n", e.getName()
					, e.getGender().getName(), e.getStatus().getStatus());
		});
	}

	@Test
	public void testEmployeeMapperDelete() {
		Employee employee = employeeMapper.getEmployeeByEmpId(1L);
		if (employee != null) {
			int row = employeeMapper.deleteEmployeeByPrimaryKey(employee.getEmpId());
			logger.info("受影响行数：{}", row);
		}
	}

	@Test
	public void testDepartmentMapperAdd() {
		Department department = new Department();
		department.setDeptName("土建学院");
		department.setDeptDesc("土建学院介绍");
		department.setDeptStatus(Constant.CommonStatus.FORBID);
		department.setCreateTime(new Date().getTime());
		department.setUpdateTime(new Date().getTime());
		int rows = departmentMapper.saveDepartment(department);
		System.out.println(rows);
		System.out.println(department.getDeptId());
	}

	@Test
	public void testDepartmentMapperSelect() {
		List<Department> allDepartment = departmentMapper.getAllDepartment();
		allDepartment.stream().forEach(e -> {
			System.out.println(e.getDeptStatus().getStatus());
		});
	}

	@Test
	public void testDepartmentMapperUpdate() {
		// 先查后改
		Department department = departmentMapper.getDepartmentByDeptId(7L);
		department.setDeptName("机电学院");
		department.setDeptStatus(Constant.CommonStatus.NORMAL);
		Long updateTime = department.getUpdateTime();
		logger.info(String.valueOf(new Date(updateTime)));
		int row = departmentMapper.updateDepartmentByPrimaryKeySelective(department);
		logger.info("受影响的行数：{}", row);
	}

	@Test
	public void testDepartmentMapperDelete() {
		// 先查后删
		Department department = departmentMapper.getDepartmentByDeptId(3L);
		if (department != null) {
			int row = departmentMapper.deleteDepartmentByPrimaryKey(department.getDeptId());
			logger.info("受影响的行数：{}", row);
		}
	}

}
