package com.project.employee.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.employee.model.EmployeeVO;


@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeVO, Integer>{

}
