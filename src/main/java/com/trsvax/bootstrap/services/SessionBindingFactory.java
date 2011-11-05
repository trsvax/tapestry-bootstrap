package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.BindingFactory;
import org.slf4j.Logger;

public class SessionBindingFactory implements BindingFactory {
	private final ApplicationStateManager state;
	private final Logger logger;

	public SessionBindingFactory(ApplicationStateManager state, Logger logger) {
		this.state = state;
		this.logger = logger;
	}

	public Binding newBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location) {
		try {
			return new SessionBinding(description,container,component,expression,location,state,logger);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
