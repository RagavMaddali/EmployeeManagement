package com.project.employee.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.exception.RecordAlreadyPresentException;
import com.project.employee.exception.RecordNotFoundException;
import com.project.employee.model.EmployeeVO;
import com.project.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeContoller {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<EmployeeVO>> readDetails(@PathVariable int id) {

		Optional<EmployeeVO> response = null;
		response = employeeService.getEmployeeDetails(id);
		if (response == null) {
			throw new RecordNotFoundException("Invalid employee id : " + id);
			//return new ResponseEntity<Optional<EmployeeVO>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<EmployeeVO>>(response, HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeVO>> readAllDetails() {
		List<EmployeeVO> response = null;

		response = employeeService.getAllEmployeeDetails();
		if (response.isEmpty()) {
			return new ResponseEntity<List<EmployeeVO>>(response, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<EmployeeVO>>(response, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeVO> insert(@RequestBody EmployeeVO employeeVO) {
		EmployeeVO response = null;

		response = employeeService.addEmployee(employeeVO);
		if (response == null) {
			throw new RecordAlreadyPresentException("Data with Id alreay exists : " + employeeVO.getId());
			//return new ResponseEntity<EmployeeVO>(response, HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<EmployeeVO>(response, HttpStatus.OK);
		}

	}
	
	@RequestMapping(method = RequestMethod.PATCH,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeVO> update(@RequestBody EmployeeVO employeeVO)
	{
		EmployeeVO response = null;
		
			response = employeeService.updateEmployee(employeeVO);
			if (response == null) {
				throw new RecordNotFoundException("Invalid employee id : " + employeeVO.getId());
				//return new ResponseEntity<EmployeeVO>(response, HttpStatus.NOT_FOUND);
			} else {
				
				return new ResponseEntity<EmployeeVO>(response, HttpStatus.OK);
			}
		
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable int id)
	{

		
			String response = employeeService.deleteEmployee(id);
			if (response != null) {
				
				return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
			} else {
				throw new RecordNotFoundException("Invalid employee id : " + id);
				//return new ResponseEntity<String>("No employee with the ID " + id, HttpStatus.NOT_FOUND);
			}
		

	}

}
