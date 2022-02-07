package com.buenoezandro.app.ws.ui.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.app.ws.ui.model.request.UserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId") Integer id) {
		return new ResponseEntity<>(new UserRest("Ezandro", "Bueno", "ezb@teste.com", id), HttpStatus.OK);
	}

	@PostMapping(consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, produces = { APPLICATION_JSON_VALUE,
			APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		return new ResponseEntity<>(new UserRest(userDetailsRequestModel.getFirstName(),
				userDetailsRequestModel.getLastName(), userDetailsRequestModel.getEmail()), HttpStatus.CREATED);
	}

	@PutMapping
	public String updateUser() {
		return "update user was called";
	}

	@DeleteMapping
	public String deletUser() {
		return "delete user was called";
	}
}
