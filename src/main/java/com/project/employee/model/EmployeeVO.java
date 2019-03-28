package com.project.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "EmployeeData")
public class EmployeeVO {
	
	
	@Id
	private Integer id;
	private String name;
	private String role;
	private String phNo;
	private Integer age;
	private String department;
	
	public EmployeeVO()
	{
		
	}
	
	public EmployeeVO(Integer id, String name, String role,String phNo, Integer age, String department) {
		
		this.id = id;
		this.name = name;
		this.role = role;
		this.phNo = phNo;
		this.age = age;
		this.department = department;
	}
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("employeeName")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("employeeRole")
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@JsonProperty("phoneNumber")
	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	@JsonProperty("Age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonProperty("DepartmentName")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
