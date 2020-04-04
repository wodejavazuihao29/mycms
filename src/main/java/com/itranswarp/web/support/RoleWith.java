package com.itranswarp.web.support;

import com.itranswarp.enums.Role;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoleWith {

	/**
	 * Get expected role.
	 */
	Role value();
}
