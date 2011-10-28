package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

public class PushEnvironment<T> {
	
	@Parameter
	private Object push;
		
 	@Parameter
 	private Class<T> clazz;
 	
 	@Inject
  	private Environment environment;
	
	@SuppressWarnings("unchecked")
	@BeginRender
	void beginRender() {
		if ( clazz == null ) {
			clazz = findInterface(push);
		}
		environment.push(clazz, (T) push);
	}
	
	@AfterRender
	void afterRender() {
		if ( clazz != null ) {
			environment.pop(clazz);
		}
	}
	
	@SuppressWarnings("unchecked")
	Class<T> findInterface(Object push) {
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		Class<?>[] interfaces = push.getClass().getInterfaces();
		for ( Class<?> c : interfaces) {
			if ( c.getName().contains("Environment")) {
				clazz = c;
			}
		}		
		return clazz;
	}

}
