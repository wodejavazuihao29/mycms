package com.itranswarp.anno;


import com.itranswarp.enums.InputType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface SettingInput {

	InputType value();

	int order();

	String description() default "";

}
