package com.trsvax.bootstrap.mixins;

import java.util.List;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.environment.Environment;

public class PushEnvironment<T> {
	
	@Parameter(required=true)
	private List<T> environments;
	
	/*
	 * Set false in layout so page can override layout defaults
	 */
	@Parameter(value="true",required=true)
	private Boolean override;
			
 	@Inject
  	private org.apache.tapestry5.services.Environment e;

	@BeginRender
	void beginRender() {
		for ( T environment : environments ) {
			if ( override || e.peek(getInterface(environment)) == null ) {
				e.push(getInterface(environment), environment);
			}
		}
	}
	
	@AfterRender
	void afterRender() {
		for ( T environment : environments ) {
			if ( override || e.peek(getInterface(environment)) == null ) {
				e.pop(getInterface(environment));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	Class<T> getInterface(T environment) {
		@SuppressWarnings("rawtypes")
		Class c = environment.getClass().getAnnotation(Environment.class).environmentInterface();
		if ( c == null ) {
			c = environment.getClass();
		} 
		return c;
	}

}
