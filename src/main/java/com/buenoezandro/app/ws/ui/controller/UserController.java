package com.buenoezandro.app.ws.ui.controller;

import static java.util.Objects.nonNull;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buenoezandro.app.ws.service.UserService;
import com.buenoezandro.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.request.UserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "Get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId") Integer id) {
		var userId = this.userService.getUserById(id);
		
		if (nonNull(userId)) {
			return new ResponseEntity<>(userId, HttpStatus.OK);
		}
		
		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(
		consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE },
		produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }
	)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		var newUser = this.userService.create(userDetailsRequestModel);

		var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(newUser.getUserId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{userId}",
		consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, 
		produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }
	)
	public UserRest updateUser(@PathVariable(value = "userId") Integer id, 
		@Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel) {
		return this.userService.update(id, updateUserDetailsRequestModel);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deletUser(@PathVariable(value = "userId") Integer id) {
		if (nonNull(id)) {
			this.userService.delete(id);
			return ResponseEntity.noContent().build();			
		}
		return ResponseEntity.notFound().build();
	}
}
