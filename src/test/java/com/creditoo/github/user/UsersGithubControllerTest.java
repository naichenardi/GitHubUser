package com.creditoo.github.user;

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

import com.creditoo.github.model.User;
import com.creditoo.github.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersGithubControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private UsersService userService;

	@Test
	public void getNoExistingUserReturnBodyNull() {
		ResponseEntity<User> response = restTemplate.getForEntity("/users/{username}", User.class, "casdiaa");
		Assertions.assertThat(response.getBody()).isNull();
	}

	@Test
	public void getExistingUserReturnStatus200() {
		User user = new User(1L, "UserTest", "User Test", "http://github.com.br", "http://github.com.br");

		BDDMockito.when(userService.getUser(user.getName())).thenReturn(user);
		ResponseEntity<User> response = restTemplate.getForEntity("/users/{username}", User.class, "UserTest");
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
