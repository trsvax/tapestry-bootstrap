package com.trsvax.bootstrap.mixins;

import java.util.List;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

public class PushEnvironment<T> {
	
	@Parameter(required=true)
	private List<T> environments;
			
 	@Inject
  	private Environment e;

	@BeginRender
	void beginRender() {
		for ( T environment : environments ) {
			e.push(getInterface(environment), environment);
		}
	}
	
	@AfterRender
	void afterRender() {
		for ( T environment : environments ) {
			e.pop(getInterface(environment));
		}
	}
	
	@SuppressWarnings("unchecked")
	Class<T> getInterface(T environment) {
		@SuppressWarnings("rawtypes")
		Class c = environment.getClass().getAnnotation(com.trsvax.bootstrap.environment.Environment.class).environmentInterface();
		if ( c == null ) {
			c = environment.getClass();
		} 
		return c;
	}

}
