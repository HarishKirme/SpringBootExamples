package com.springboot.rest.webservices.Demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.webservices.Demo.Entity.HelloWordUser;

@RestController
public class HelloWorlController {

	
	@RequestMapping(method = RequestMethod.GET,path = "/hello-world")
	public String helloPrint() {
		
		return "Hello World";
	}
		
	
	@RequestMapping(method = RequestMethod.GET,path="/hello-world-bean")
	public HelloWordUser helloBean() {
		
		return new HelloWordUser("Hello HelloWordUser Bean");
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/hello-path/{name}")
	public HelloWordUser helloPathVariable(@PathVariable String name) {
		
		
		return new HelloWordUser(String.format("Hello path variable is %s", name));
	}
	
	
	
}
