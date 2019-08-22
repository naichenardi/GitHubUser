package com.creditoo.github.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubErrorJson implements Serializable {
	private static final long serialVersionUID = -7727548456008704987L;

	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("message")
	private String message;
	
	public GithubErrorJson(final GithubException ex) {
		super();
		this.status = ex.getStatus();
		this.message = ex.getMessage();
	}

	public GithubErrorJson() {
		super();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
