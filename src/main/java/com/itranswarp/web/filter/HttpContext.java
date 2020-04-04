package com.itranswarp.web.filter;

import com.itranswarp.common.ApiException;
import com.itranswarp.enums.ApiError;
import com.itranswarp.enums.Role;
import com.itranswarp.model.User;
import com.itranswarp.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpContext implements AutoCloseable {

	static final Logger logger = LoggerFactory.getLogger(HttpContext.class);

	static final ThreadLocal<HttpContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

	public final User user;
	public final long timestamp;
	public final HttpServletRequest request;
	public final HttpServletResponse response;
	public final String scheme;
	public final String host;
	public final String path;
	public final String url;

	HttpContext(User user, HttpServletRequest request, HttpServletResponse response) {
		this.user = user;
		this.timestamp = System.currentTimeMillis();
		this.request = request;
		this.response = response;
		this.scheme = HttpUtil.getScheme(request);
		this.host = request.getServerName().toLowerCase();
		this.path = request.getRequestURI();
		String query = request.getQueryString();
		this.url = this.scheme + "://" + this.host + this.path + (query == null ? "" : "?" + query);
		logger.info("process new http context: {} {}...", request.getMethod(), this.url);
		CONTEXT_THREAD_LOCAL.set(this);
	}

	public static HttpContext getContext() {
		return CONTEXT_THREAD_LOCAL.get();
	}

	public static long getTimestamp() {
		return CONTEXT_THREAD_LOCAL.get().timestamp;
	}

	public static User getCurrentUser() {
		return CONTEXT_THREAD_LOCAL.get().user;
	}

	public static User getRequiredCurrentUser() {
		User user = getCurrentUser();
		if (user == null) {
			throw new ApiException(ApiError.AUTH_SIGNIN_REQUIRED, null, "Need signin first.");
		}
		return user;
	}

	public static void checkRole(Role expectedRole) {
		User user = getRequiredCurrentUser();
		if (user.role.value > expectedRole.value) {
			throw new ApiException(ApiError.PERMISSION_DENIED);
		}
	}

	@Override
	public void close() {
		CONTEXT_THREAD_LOCAL.remove();
	}
}
