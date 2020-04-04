package com.itranswarp.web.view;

import com.mitchellbosecke.pebble.extension.Filter;

import java.util.List;

public abstract class AbstractFilter implements Filter {

	public abstract String getName();

	@Override
	public List<String> getArgumentNames() {
		return null;
	}

}
