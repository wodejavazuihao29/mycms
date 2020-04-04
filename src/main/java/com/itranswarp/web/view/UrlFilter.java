package com.itranswarp.web.view;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class UrlFilter extends AbstractFilter {

	@Override
	public String getName() {
		return "url";
	}

	@Override
	public Object apply(Object input, Map<String, Object> args, PebbleTemplate self, EvaluationContext context,
                        int lineNumber) throws PebbleException {
		if (input == null) {
			return "";
		}
		String s = input.toString();
		return URLEncoder.encode(s, StandardCharsets.UTF_8).replace("+", "%20");
	}

}
