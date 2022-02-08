package com.buenoezandro.app.ws.service;

import com.buenoezandro.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.request.UserDetailsRequestModel;
import com.buenoezandro.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest getUserById(Integer id);
	UserRest create(UserDetailsRequestModel userDetailsRequestModel);
	UserRest update(Integer id, UpdateUserDetailsRequestModel updateUserDetailsRequestModel);
	void delete(Integer id);
}
