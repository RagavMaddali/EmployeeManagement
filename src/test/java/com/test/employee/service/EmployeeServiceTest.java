package com.test.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.employee.dao.EmployeeRepository;
import com.project.employee.model.EmployeeVO;
import com.project.employee.service.EmployeeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EmployeeService.class })
public class EmployeeServiceTest {
	@MockBean
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	@Test
	public void addEmployeeTest()
	{
		EmployeeVO employeeVO = new EmployeeVO(100, "ragav100", "dev", "8438938945", 24, "Dev-dev");
		Mockito.when(employeeRepository.save(employeeVO)).thenReturn(employeeVO);
		EmployeeVO response = employeeService.addEmployee(employeeVO);
		assert(response.getId()==employeeVO.getId());
		assert(response.getAge()==employeeVO.getAge());
		assert(response.getName().matches(employeeVO.getName()));		
		assert(response.getDepartment().matches(employeeVO.getDepartment()));
		assert(response.getPhNo().matches(employeeVO.getPhNo()));
		assert(response.getRole().matches(employeeVO.getRole()));		
	}
	@Test
	public void updateEmployeeTest()
	{
		EmployeeVO employeeVO = new EmployeeVO(100, "ragav100", "dev", "8438938945", 24, "Dev-dev");
		
		Mockito.when(employeeRepository.existsById(employeeVO.getId())).thenReturn(true);
		Mockito.when(employeeRepository.save(employeeVO)).thenReturn(employeeVO);
		EmployeeVO response = employeeService.updateEmployee(employeeVO);
		assert(response.getId()==employeeVO.getId());
		assert(response.getAge()==employeeVO.getAge());
		assert(response.getName().matches(employeeVO.getName()));		
		assert(response.getDepartment().matches(employeeVO.getDepartment()));
		assert(response.getPhNo().matches(employeeVO.getPhNo()));
		assert(response.getRole().matches(employeeVO.getRole()));	
	}
	@Test
	public void deleteEmployeeTest()
	{
		EmployeeVO employeeVO = new EmployeeVO(100, "ragav100", "dev", "8438938945", 24, "Dev-dev");
		List<EmployeeVO> list = new ArrayList<>();
		list.add(employeeVO);
		Mockito.when(employeeRepository.findAll()).thenReturn(list);
		List<EmployeeVO> response = employeeService.getAllEmployeeDetails();
		assert(response.size()==1);
		assert(response.get(0).getId()==list.get(0).getId());
		assert(response.get(0).getAge()==list.get(0).getAge());
		assert(response.get(0).getName().matches(employeeVO.getName()));		
		assert(response.get(0).getDepartment().matches(employeeVO.getDepartment()));
		assert(response.get(0).getPhNo().matches(employeeVO.getPhNo()));
		assert(response.get(0).getRole().matches(employeeVO.getRole()));
	}
}
