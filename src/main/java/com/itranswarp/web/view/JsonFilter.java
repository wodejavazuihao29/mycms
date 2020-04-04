package com.itranswarp.web.view;

import com.itranswarp.util.JsonUtil;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonFilter extends AbstractFilter {

	@Override
	public String getName() {
		return "json";
	}

	@Override
	public Object apply(Object input, Map<String, Object> args, PebbleTemplate self, EvaluationContext context,
                        int lineNumber) throws PebbleException {
		return JsonUtil.writeJson(input);
	}

}
