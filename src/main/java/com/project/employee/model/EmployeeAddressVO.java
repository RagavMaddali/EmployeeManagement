package com.project.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "EmployeeAddress")
public class EmployeeAddressVO {
	@Id
	private int id;	
	private String address;
	
	public EmployeeAddressVO()
	{
		
	}
	
	public EmployeeAddressVO(int id, String address) { 
		this.id = id;
		this.address = address;
	}
	
	
	@JsonProperty("id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
