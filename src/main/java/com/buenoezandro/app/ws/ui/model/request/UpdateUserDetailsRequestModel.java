package com.buenoezandro.app.ws.ui.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 2, message = "First name must not be less than 2 characters")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	@Size(min = 2, message = "Last name must not be less than 2 characters")
	private String lastName;

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
}
