package com.project.employee.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.exception.RecordNotFoundException;
import com.project.employee.model.EmployeeVO;
import com.project.employee.model.ViewEmployeeVO;
import com.project.employee.service.EmployeeService;
import com.project.employee.storage.MessageStorage;
import com.project.employee.util.Utility;

@RestController
@RequestMapping("/viewemployee")
public class ViewEmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	  MessageStorage storage;
	
	@Autowired
	Utility utility;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ViewEmployeeVO> readDetails(@PathVariable int id) {

		
		
		ViewEmployeeVO response = null;
		Optional<EmployeeVO> employeeVO = employeeService.getEmployeeDetails(id); 
		if (employeeVO == null) {
			throw new RecordNotFoundException("Invalid employee id : " + id);
			//return new ResponseEntity<ViewEmployeeVO>(response, HttpStatus.NOT_FOUND);
		} else {
			String address = storage.get(id);
			response = new ViewEmployeeVO();
			utility.copyEmployeeProperties(response, employeeVO.get());
			response.setAddress(address);
			return new ResponseEntity<ViewEmployeeVO>(response, HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ViewEmployeeVO>> readAllDetails() {
		List<EmployeeVO> list = null;
		list = employeeService.getAllEmployeeDetails();
		if (list.isEmpty()) {
			return new ResponseEntity<List<ViewEmployeeVO>>(new ArrayList<ViewEmployeeVO>(), HttpStatus.NO_CONTENT);
		} else {
			List<ViewEmployeeVO> response = new ArrayList<ViewEmployeeVO>(list.size());
			ViewEmployeeVO viewEmployeeVO = null ;
			for(EmployeeVO employeeVO : list)
			{
				viewEmployeeVO = new ViewEmployeeVO();
				utility.copyEmployeeProperties(viewEmployeeVO, employeeVO);
				viewEmployeeVO.setAddress(storage.get(employeeVO.getId()));
				response.add(viewEmployeeVO);
			}
			
			return new ResponseEntity<List<ViewEmployeeVO>>(response, HttpStatus.OK);
		}
	}
	
}
