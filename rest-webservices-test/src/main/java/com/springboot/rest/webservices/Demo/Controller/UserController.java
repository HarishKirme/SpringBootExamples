package com.springboot.rest.webservices.Demo.Controller;

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

import com.springboot.rest.webservices.Demo.Entity.User;
import com.springboot.rest.webservices.Demo.Services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, path="/getAllUsers")
	public List<User> getAllUser() {
		
		return userService.findAllUser();
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET , path = "/getUsrById/{id}")
	public User getUserById(@PathVariable int id, @RequestHeader("host") String hostName
												, @RequestHeader("User-Agent") String userAgent) {
		
		System.out.println("in getUserById"+ id);
		 
		return userService.findUserById(id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/createNewUser")
	public ResponseEntity<Object> createNewUser(@RequestBody User user) {
		
		System.out.println("in createNewUser" + user);
	
		 int k = 
		userService.newUser(user);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(k).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/updateUser/{id}")
	public void updateUser(@PathVariable int id, @RequestBody User user) {
		
		userService.updateUser(id,user);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE,path = "/deleteUser/{name}")
	public void deleteUser(@PathVariable String name) {
		
		userService.delete(name);
		
	}
		
	
	
	@RequestMapping(method = RequestMethod.PATCH, path ="/updateOnlySchool/{id}")
	public void updateOnlySchool(@RequestParam String school,@PathVariable int id) {
		
		userService.updateSchoolService(id, school);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
}
