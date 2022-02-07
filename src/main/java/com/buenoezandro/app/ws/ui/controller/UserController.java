package com.buenoezandro.app.ws.ui.controller;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buenoezandro.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.request.UserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	private static final int BOUND = 100;
	private Map<Integer, UserRest> users;
	private Random random = new Random();

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId") Integer id) {

		if (this.users.containsKey(id)) {
			return new ResponseEntity<>(this.users.get(id), HttpStatus.OK);
		}

		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(
		consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE },
		produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }
	)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		Integer id = this.random.nextInt(BOUND);

		if (isNull(this.users)) {
			this.users = new HashMap<>();
			this.users.put(id, 
				new UserRest(userDetailsRequestModel.getFirstName(),
					userDetailsRequestModel.getLastName(), 
					userDetailsRequestModel.getEmail(), id)
				);
		}

		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{userId}",
		consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, 
		produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }
	)
	public UserRest updateUser(@PathVariable(value = "userId") Integer id, 
		@Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel) {
		var storedUserDetails = this.users.get(id);
		storedUserDetails.setFirstName(updateUserDetailsRequestModel.getFirstName());
		storedUserDetails.setLastName(updateUserDetailsRequestModel.getLastName());
		
		this.users.put(id, storedUserDetails);
		return storedUserDetails;
	}

	@DeleteMapping
	public String deletUser() {
		return "delete user was called";
	}
}
