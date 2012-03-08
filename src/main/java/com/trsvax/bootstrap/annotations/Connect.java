package com.trsvax.bootstrap.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Connect the following field to the value of another property
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Connect {
	
	public String value();

}
 