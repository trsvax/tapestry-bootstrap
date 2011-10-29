package com.trsvax.bootstrap.environment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Environment {

	public Class<?> environmentInterface();
}
