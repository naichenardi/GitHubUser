package com.creditoo.github.service;

import com.creditoo.github.model.User;

/**
 * Interface to users services
 */
public interface UsersService {

	/**
	 * Method to get informations about github users
	 * 
	 * @param userName - Github user name
	 * @return {@link User}
	 * @throws Exception
	 */
	User getUser(String userName);

}
