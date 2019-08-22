package com.creditoo.github.service;

import java.util.List;

import com.creditoo.github.model.Repository;

/**
 * Interface to repositories services
 */
public interface RepositoriesService {

	/**
	 * Method to get repositories by user
	 * 
	 * @param username - User name on github
	 * @return {@link Repository}
	 */
	List<Repository> getRepositoriesByUserName(String username);
}
