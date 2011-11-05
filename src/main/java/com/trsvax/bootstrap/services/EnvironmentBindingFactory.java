package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.Environment;

public class EnvironmentBindingFactory implements BindingFactory {
	private final Environment environment;
	
	public EnvironmentBindingFactory(Environment environment) {
		this.environment = environment;
	}

	public Binding newBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location) {
		try {
			return new EnvironmentBinding(description,container,component,expression,location,environment);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
