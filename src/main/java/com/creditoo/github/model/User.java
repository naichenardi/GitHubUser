package com.creditoo.github.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Users model class of github
 */
public class User implements Serializable {
	private static final long serialVersionUID = -8377053527263053778L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("login")
	private String login;

	@JsonProperty("name")
	private String name;

	@JsonProperty("avatar_url")
	private String avatarUrl;

	@JsonProperty("html_url")
	private String htmlUrl;

	public User() {
		super();
	}

	public User(Long id, String login, String name, String avatarUrl, String htmlUrl) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.avatarUrl = avatarUrl;
		this.htmlUrl = htmlUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name + ", avatarUrl=" + avatarUrl + ", htmlUrl="
				+ htmlUrl + "]";
	}
}
