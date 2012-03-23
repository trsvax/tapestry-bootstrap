package com.trsvax.bootstrap.services;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

@SuppressWarnings("rawtypes")
public class EnvironmentSetupImpl implements EnvironmentSetup {
	private final Map<Class,Object> setup;
	private final Environment environment;
	
	public EnvironmentSetupImpl(Map<Class,Object> setup, Logger logger, Environment environment) {
		this.setup = setup;
		this.environment = environment;
	}
	
	@SuppressWarnings("unchecked")
	public void push() {
		for ( Entry<Class, Object> entry : setup.entrySet() ) {
			environment.push(entry.getKey(), entry.getValue());
		}
	}
	@SuppressWarnings("unchecked")
	public void pop() {
		for ( Entry<Class, Object> entry : setup.entrySet() ) {
			environment.pop(entry.getKey());
		}
	}

}
