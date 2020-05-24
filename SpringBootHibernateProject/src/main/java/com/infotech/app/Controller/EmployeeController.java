package com.infotech.app.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.infotech.app.Dao.EmployeeDao;
import com.infotech.app.Entities.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping(method = RequestMethod.GET, path = "/getAllEmployees")
	public List<Employee> getAllEmployee() {

		return employeeDao.findAllEmployee();

	}

	@RequestMapping(method = RequestMethod.GET, path = "/getEmployeeById/{id}")
	public Employee getEmployeeById(@PathVariable int id/*
														 * , @RequestHeader("host") String hostName
														 * , @RequestHeader("Employee-Agent") String userAgent
														 */) {

		System.out.println("in getEmployeeById" + id);

		return employeeDao.findEmployeeById(id);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/createNewEmployee")
	public ResponseEntity<Object> createNewEmployee(@RequestBody Employee user) {

		System.out.println("in createNewEmployee" + user);

		int k = employeeDao.newEmployee(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(k).toUri();

		return ResponseEntity.created(location).build();
	}

	
	  @RequestMapping(method = RequestMethod.PUT, path = "/updateEmployee/{id}")
	  public void updateEmployee(@PathVariable int id, @RequestBody Employee user)
	  {
	  
	  employeeDao.updateEmployee(id,user);
	  
	  }
	  
	  @RequestMapping(method = RequestMethod.DELETE,path = "/deleteEmployee/{name}")
	  public void deleteEmployee(@PathVariable String name) {
	  
	  employeeDao.delete(name);
	  
	  }
	  
	  
	  
	  @RequestMapping(method = RequestMethod.PATCH, path ="/updateOnlySalary/{id}")
	  public void updateOnlySchool(@RequestParam Double salary,@PathVariable int
	  id) {
	  
	  employeeDao.updateSalaryDao(id, salary); 
	  }
	 

}
