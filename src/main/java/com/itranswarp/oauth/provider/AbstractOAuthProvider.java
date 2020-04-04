package com.itranswarp.oauth.provider;

import com.itranswarp.oauth.OAuthAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.time.Duration;

public abstract class AbstractOAuthProvider {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);

	/**
	 * Get lower-case provider id as unique identity.
	 */
	public final String getProviderId() {
		String className = getClass().getSimpleName();
		if (className.endsWith("OAuthProvider")) {
			return className.substring(0, className.length() - "OAuthProvider".length()).toLowerCase();
		}
		throw new IllegalArgumentException("Could not get provider name from class name: " + className);
	}

	public boolean isEnabled() {
		AbstractOAuthConfiguration conf = getOAuthConfiguration();
		String clientId = conf.getClientId();
		String clientSecret = conf.getClientSecret();
		return clientId != null && !clientId.isEmpty() && clientSecret != null && !clientSecret.isEmpty();
	}

	public abstract AbstractOAuthConfiguration getOAuthConfiguration();

	public abstract String getAuthenticateUrl(String redirectUrl);

	public abstract OAuthAuthentication getAuthentication(String code, String state, String redirectUrl)
			throws Exception;

	protected final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(DEFAULT_TIMEOUT)
			.followRedirects(HttpClient.Redirect.NEVER).build();
}
