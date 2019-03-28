package com.project.employee.util;

import org.springframework.stereotype.Service;

import com.project.employee.model.EmployeeVO;
import com.project.employee.model.ViewEmployeeVO;

@Service
public class Utility {
	public void copyEmployeeProperties(ViewEmployeeVO dest , EmployeeVO source)
	{
		dest.setId(source.getId());
		dest.setName(source.getName());
		dest.setRole(source.getRole());
		dest.setPhNo(source.getPhNo());
		dest.setAge(source.getAge());
		dest.setDepartment(source.getDepartment());
	}
	
	public Utility()
	{
		
	}
}
