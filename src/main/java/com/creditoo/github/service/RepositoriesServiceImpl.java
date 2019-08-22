package com.creditoo.github.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.creditoo.github.exception.GithubException;
import com.creditoo.github.model.Repository;
import com.creditoo.github.resourcebundle.MessageUtils;
import com.creditoo.github.util.RestUtils;

/**
 * Implementation repositories services.
 */
@Service
public class RepositoriesServiceImpl implements RepositoriesService {

	@Autowired
	private RestUtils restUtils;

	@Override
	public List<Repository> getRepositoriesByUserName(String username) {
		if (Objects.isNull(username) || username.isEmpty()) {
			throw new GithubException(HttpStatus.BAD_REQUEST.value(),
					MessageUtils.getMessage("message.invalid_parameter"));
		}

		Map<String, Object> params = new HashMap<>();

		params.put("username", username);

		Repository[] repositoryList = restUtils.getWithPathVariable("users/{username}/repos", Repository[].class,
				params);

		return Arrays.asList(repositoryList);
	}

}
