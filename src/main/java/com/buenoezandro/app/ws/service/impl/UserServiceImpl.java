package com.buenoezandro.app.ws.service.impl;

import static com.buenoezandro.app.ws.shared.Utils.generateUserId;
import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.buenoezandro.app.ws.exceptions.UserServiceException;
import com.buenoezandro.app.ws.service.UserService;
import com.buenoezandro.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.request.UserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.response.UserRest;

@Service
public class UserServiceImpl implements UserService {
	private Map<Integer, UserRest> users;
	
	@Override
	public UserRest getUserById(Integer id) {
		if (this.users.containsKey(id)) {
			return this.users.get(id);
		}
		return null;
	}

	@Override
	public UserRest create(UserDetailsRequestModel userDetailsRequestModel) {
		int id = generateUserId();

		var user = new UserRest(
				userDetailsRequestModel.getFirstName(), 
				userDetailsRequestModel.getLastName(),
				userDetailsRequestModel.getEmail(), 
				id);

		if (isNull(this.users)) {
			this.users = new HashMap<>();
			this.users.put(id, user);
		}

		return user;
	}

	@Override
	public UserRest update(Integer id, UpdateUserDetailsRequestModel updateUserDetailsRequestModel) {
		var storedUserDetails = this.findByIdOrThrow(id);
		
		storedUserDetails.setFirstName(updateUserDetailsRequestModel.getFirstName());
		storedUserDetails.setLastName(updateUserDetailsRequestModel.getLastName());
		
		this.users.put(id, storedUserDetails);
		return storedUserDetails;
	}

	@Override
	public void delete(Integer id) {
		this.findByIdOrThrow(id);
		this.users.remove(id);
	}
	
	private UserRest findByIdOrThrow(Integer id) {
		if (isNull(this.getUserById(id))) {
			throw new UserServiceException("ID: " + id + " not found!"); 
		}
		return this.users.get(id);
	}
}
