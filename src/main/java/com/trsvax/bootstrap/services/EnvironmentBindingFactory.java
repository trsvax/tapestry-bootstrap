package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.internal.util.TapestryException;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.PropertyConduitSource;

public class EnvironmentBindingFactory implements BindingFactory {
	private final Environment environment;
	private final PropertyConduitSource source;
	
	public EnvironmentBindingFactory(Environment environment,PropertyConduitSource propertyConduitSource) {
		this.environment = environment;
		this.source = propertyConduitSource;
	}

	public Binding newBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location) {

		try {
			Integer index = expression.lastIndexOf(".");
			String className = expression.substring(0, index);
			String propertyExpression = expression.substring(index+1);
			Class<?> enviromentInterface = Class.forName(className);
	        PropertyConduit conduit = source.create(enviromentInterface, propertyExpression);
	
	        //StringInterner is internal
	        String toString = String.format("EnvironmentBinding[%s %s(%s)]", description, container
	                .getCompleteId(), expression);
	        return new EnvironmentBinding(location,conduit,toString,environment,enviromentInterface);
		} catch (ClassNotFoundException ex) {
			throw new TapestryException(ex.getMessage(), location, ex);
		}

	}

}
