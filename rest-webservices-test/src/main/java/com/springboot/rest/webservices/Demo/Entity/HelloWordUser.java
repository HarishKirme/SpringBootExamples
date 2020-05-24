package com.springboot.rest.webservices.Demo.Entity;


public class HelloWordUser {

    private String string;

	public HelloWordUser(String string) {
		
		this.string = string;
	}

	/**
	 * @param string the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}
	

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HelloWordUser [string=" + string + "]";
	}



}
