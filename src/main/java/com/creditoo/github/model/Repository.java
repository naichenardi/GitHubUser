package com.creditoo.github.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Repository model class of github
 */
public class Repository implements Serializable{
	private static final long serialVersionUID = 3869781309321844674L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("html_url")
	private String htmlUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	@Override
	public String toString() {
		return "Repositories [id=" + id + ", name=" + name + ", description=" + description + ", htmlUrl=" + htmlUrl
				+ "]";
	}

	public Repository(Long id, String name, String description, String htmlUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.htmlUrl = htmlUrl;
	}

	public Repository() {
		super();
	}
}
