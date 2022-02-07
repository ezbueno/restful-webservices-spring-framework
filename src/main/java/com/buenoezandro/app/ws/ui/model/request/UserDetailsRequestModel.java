package com.buenoezandro.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 2, message = "First name must not be less than 2 characters")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	@Size(min = 2, message = "Last name must not be less than 2 characters")
	private String lastName;

	@NotEmpty(message = "Email cannot be empty")
	@Email
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
