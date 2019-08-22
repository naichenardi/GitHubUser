package com.creditoo.github.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.creditoo.github.exception.GithubException;
import com.creditoo.github.resourcebundle.MessageUtils;

@Service
public class RestUtils {

	@Autowired
	private Environment env;

	private HttpEntity<String> header() {
		final HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=utf-8");
		header.add("Accept", "application/json;charset=utf-8");

		return new HttpEntity<>(header);
	}

	private String getGitUriConcatUrl(String url) {
		String gitUri = env.getProperty("githubURI");

		if (Objects.nonNull(gitUri) && !gitUri.isEmpty()) {
			return gitUri.concat(url);
		} else {
			throw new GithubException(url, HttpStatus.BAD_GATEWAY.value(),
					MessageUtils.getMessage("message.url_git_not_fount"));
		}
	}

	private String paramsResolve(String url, Map<String, Object> params) {
		StringBuilder adress = new StringBuilder();
		adress.append(getGitUriConcatUrl(url));
		if (Objects.nonNull(params) && params.size() > 0) {
			adress.append("?");
			for (Entry<String, ?> entry : params.entrySet()) {
				if ('?' == adress.toString().charAt(adress.toString().length() - 1)) {
					adress.append(entry.getKey() + "=" + entry.getValue());
				} else {
					adress.append("&" + entry.getKey() + "=" + entry.getValue());
				}
			}
		}

		return adress.toString();
	}

	public <T> T getWithPathParams(String url, Class<T> type, Map<String, Object> params) throws GithubException {

		ResponseEntity<T> response;
		try {

			response = new RestTemplate().exchange(paramsResolve(url, params), HttpMethod.GET, header(), type);
		} catch (final HttpClientErrorException e) {
			throw new GithubException(e.getStatusCode().value(), e.getResponseBodyAsString());
		}
		return response.getBody();
	}

	public <T> T getWithPathVariable(String url, Class<T> type, Map<String, Object> params) throws GithubException {
		ResponseEntity<T> response;
		try {
			response = new RestTemplate().exchange(getGitUriConcatUrl(url), HttpMethod.GET, header(), type, params);
		} catch (final HttpClientErrorException e) {
			throw new GithubException(e.getStatusCode().value(), e.getResponseBodyAsString());
		}
		return response.getBody();
	}

}
