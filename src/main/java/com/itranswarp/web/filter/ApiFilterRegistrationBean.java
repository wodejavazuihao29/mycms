package com.itranswarp.web.filter;

import com.itranswarp.common.ApiException;
import com.itranswarp.enums.ApiError;
import com.itranswarp.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class ApiFilterRegistrationBean extends FilterRegistrationBean<Filter> {

	@PostConstruct
	public void init() {
		setOrder(200);
		setUrlPatterns(List.of("/api/*"));
		setFilter(new ApiFilter());
	}

	class ApiFilter implements Filter {

		final Logger logger = LoggerFactory.getLogger(getClass());

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			try {
				chain.doFilter(request, response);
			} catch (ApiException e) {
				sendApiError(httpResponse, e);
			} catch (Exception e) {
				logger.warn("exception.", e);
				Throwable cause = e.getCause();
				if (cause instanceof ApiException) {
					sendApiError(httpResponse, (ApiException) cause);
				} else {
					sendApiError(httpResponse, new ApiException(ApiError.INTERNAL_SERVER_ERROR, null, e.getMessage()));
				}
			}
		}

		void sendApiError(HttpServletResponse httpResponse, ApiException e) throws IOException {
			if (httpResponse.isCommitted()) {
				logger.error("Cannot send API error because the reponse is already committed.");
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				httpResponse.setContentType("application/json");
				@SuppressWarnings("resource")
				PrintWriter pw = httpResponse.getWriter();
				pw.write(JsonUtil.writeJson(e.toMap()));
				pw.flush();
			}
		}
	}
}
