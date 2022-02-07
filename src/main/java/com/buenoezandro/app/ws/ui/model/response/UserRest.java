package com.buenoezandro.app.ws.ui.model.response;

public class UserRest {
	private String firstName;
	private String lastName;
	private String email;
	private String userId;
	
	public UserRest() {
	}
	
	public UserRest(String firstName, String lastName, String email, String userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
