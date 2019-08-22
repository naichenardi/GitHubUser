package com.creditoo.github.user;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditoo.github.model.Repository;
import com.creditoo.github.service.RepositoriesService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RepositoriesGithubControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private RepositoriesService service;

	@Test
	public void getNoExistingUserReturnBodyNull() {
		ResponseEntity<Repository[]> response = restTemplate.getForEntity("/users/{username}/repos", Repository[].class, "casdiaa");
		Assertions.assertThat(response.getBody()).isNullOrEmpty();
	}

	@Test
	public void getExistingUserReturnStatus200() {
		List<Repository> repositoryList = Arrays.asList(
				new Repository(1L, "Projeto1", "Projeto um", "http://www.projeto1.com"),
				new Repository(2L, "Projeto2", "Projeto dois", "http://www.projeto2.com"));
		BDDMockito.when(service.getRepositoriesByUserName("UserTest")).thenReturn(repositoryList);
		ResponseEntity<Repository[]> response = restTemplate.getForEntity("/users/{username}/repos", Repository[].class,
				"UserTest");
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
