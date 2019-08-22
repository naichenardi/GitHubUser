package com.creditoo.github.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.creditoo.github.exception.GithubException;
import com.creditoo.github.model.User;
import com.creditoo.github.resourcebundle.MessageUtils;
import com.creditoo.github.util.RestUtils;

/**
 * Implementation users services.
 */
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private RestUtils restUtils;

	@Override
	public User getUser(String username) {
		if (Objects.isNull(username) || username.isEmpty()) {
			throw new GithubException(HttpStatus.BAD_REQUEST.value(),
					MessageUtils.getMessage("message.invalid_parameter"));
		}

		Map<String, Object> params = new HashMap<>();

		params.put("username", username);

		User user = restUtils.getWithPathVariable("users/{username}", User.class, params);

		if (user == null) {
			throw new GithubException(HttpStatus.NOT_FOUND.value(), MessageUtils.getMessage("message.user_not_found"));
		}

		return user;
	}

}
