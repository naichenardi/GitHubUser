package com.creditoo.github.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.creditoo.github.exception.GithubErrorJson;
import com.creditoo.github.exception.GithubException;
import com.creditoo.github.resourcebundle.MessageUtils;

@ControllerAdvice
public class GithubExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GithubException.class)
	private ResponseEntity<GithubErrorJson> errorHandler(final GithubException ex) {
		if (HttpStatus.NOT_FOUND.value() == ex.getStatus()) {
			ex.setMessage(MessageUtils.getMessage("message.resource_not_found"));
			return new ResponseEntity<>(new GithubErrorJson(ex), HttpStatus.valueOf(ex.getStatus()));
		}
		return new ResponseEntity<>(new GithubErrorJson(ex), HttpStatus.valueOf(ex.getStatus()));
	}

	@ExceptionHandler(HttpClientErrorException.class)
	private ResponseEntity<GithubErrorJson> errorHandler(final HttpClientErrorException ex) {
		GithubErrorJson githubError = new GithubErrorJson();

		githubError.setStatus(ex.getStatusCode().value());
		githubError.setMessage(ex.getMessage());

		return new ResponseEntity<>(githubError, ex.getStatusCode());
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<GithubErrorJson> errorHandler(final Exception ex) {
		GithubErrorJson githubError = new GithubErrorJson();

		githubError.setStatus(HttpStatus.BAD_GATEWAY.value());
		githubError.setMessage(MessageUtils.getMessage("message.incorrect syntax"));

		return new ResponseEntity<>(githubError, HttpStatus.BAD_GATEWAY);
	}
}
