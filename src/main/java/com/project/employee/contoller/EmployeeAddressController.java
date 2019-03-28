package com.project.employee.contoller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.exception.RecordNotFoundException;
import com.project.employee.model.EmployeeAddressVO;
import com.project.employee.model.EmployeeVO;
import com.project.employee.service.EmployeeService;
import com.project.employee.service.KafkaProducer;
import com.project.employee.storage.MessageStorage;

@RestController
@RequestMapping(value="/employeeAddress")
public class EmployeeAddressController {

	  @Autowired
	  KafkaProducer producer;
	  
	  @Autowired
	  MessageStorage storage;
	  
	  @Autowired
	  EmployeeService employeeService;
	  
	  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<EmployeeAddressVO> insert(@RequestBody EmployeeAddressVO employeeAddressVO) {
		  
		 if(employeeService.checkEmployeeId(employeeAddressVO.getId()))
		 {
			 producer.send(    employeeAddressVO.getId() +":"+ employeeAddressVO.getAddress());
			 return new ResponseEntity<EmployeeAddressVO>(employeeAddressVO, HttpStatus.OK);
		 }
		 throw new RecordNotFoundException("Invalid employee id : " + employeeAddressVO.getId());
		 //return new ResponseEntity<String>("Employee record not present", HttpStatus.NOT_FOUND);
	    
	  }
	  
	  
	  @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> readDetails(@PathVariable int id) {

			String response = null;
			response = storage.get(id);
			if (response == null) {

				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}

		}
}
