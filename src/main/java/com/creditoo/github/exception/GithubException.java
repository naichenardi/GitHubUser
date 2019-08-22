package com.creditoo.github.exception;

public class GithubException extends RuntimeException{
	private static final long serialVersionUID = 3390579789290287824L;

	private String url;
	
	private Integer status;
	
	private String message;
	
	public GithubException(String url, Integer status, String message) {
		super();
		this.url = url;
		this.status = status;
		this.message = message;
	}
	
	public GithubException(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [url=" + url + ", status=" + status + ", message=" + message + "]";
	}
}
