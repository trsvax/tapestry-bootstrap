package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

public class PushEnvironment<T> {
	
	@Parameter(required=true)
	@Property
	private T environment;
		
 	@Parameter
 	private Class<T> environmentInterface;
 	
 	@Inject
  	private Environment e;
 	
 	@Inject
 	private Logger logger;
	
	@BeginRender
	void beginRender() {
		if ( environmentInterface == null ) {
			environmentInterface = getInterface();
		}
		logger.info("push {}",environmentInterface.getName());
		e.push(environmentInterface, environment);
	}
	
	@AfterRender
	void afterRender() {
		e.pop(environmentInterface);
	}
	
	@SuppressWarnings("unchecked")
	Class<T> getInterface() {
		@SuppressWarnings("rawtypes")
		Class i = environment.getClass().getAnnotation(com.trsvax.bootstrap.environment.Environment.class).environmentInterface();
		return i;
	}

}
