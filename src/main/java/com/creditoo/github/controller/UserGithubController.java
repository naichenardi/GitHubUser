package com.creditoo.github.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditoo.github.model.Repository;
import com.creditoo.github.model.User;
import com.creditoo.github.service.RepositoriesService;
import com.creditoo.github.service.UsersService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/users")
@Api(value = "Users")
public class UserGithubController {

	@Autowired
	private UsersService userService;

	@Autowired
	private RepositoriesService repositoriesService;

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User getUser(@PathVariable(value = "username") String username) {
		return userService.getUser(username);
	}

	@GetMapping(value = "/{username}/repos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Repository> getRepositories(@PathVariable(value = "username") String username) {
		return repositoriesService.getRepositoriesByUserName(username);
	}
}
