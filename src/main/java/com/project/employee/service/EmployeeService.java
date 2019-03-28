package com.project.employee.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.employee.dao.EmployeeRepository;
import com.project.employee.model.EmployeeVO;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public EmployeeVO addEmployee(EmployeeVO employee) {
		EmployeeVO response = null;
		if (employee != null) {
			if (!employeeRepository.existsById(employee.getId())) {
				response = employeeRepository.save(employee);
				logger.info("new employee record added " + employee.getId());
			} else {
				logger.info("Employee record already exists");
			}
		}
		return response;
	}

	public Optional<EmployeeVO> getEmployeeDetails(Integer id) {

		if (employeeRepository.existsById(id)) {
			logger.info("employee data fetched with id : " + id);
			return employeeRepository.findById(id);
		} else {
			return null;
		}
	}

	public List<EmployeeVO> getAllEmployeeDetails() {
		logger.info("all employee data fetched");
		return employeeRepository.findAll();
	}

	public EmployeeVO updateEmployee(EmployeeVO employeeVO) {
		EmployeeVO response = null;
		if (employeeVO != null) {
			if (employeeRepository.existsById(employeeVO.getId())) {
				response = employeeRepository.save(employeeVO);
				logger.info("employee data updated with id :" + employeeVO.getId());
				return response;
			} else {
				logger.info("employee data with id :" + employeeVO.getId() + " does not exists");
				return null;
			}
		}
		else
		{
			return null;
		}
		
	}

	public String deleteEmployee(int id) {

		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			logger.info("employee data deleted with id :" + id);
			return "employee with id " + id + " deleted";
		} else {
			return null;
		}
	}
	
	public Boolean checkEmployeeId(int id)
	{
		if (employeeRepository.existsById(id)) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
