package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.internal.util.TapestryException;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.PropertyConduitSource;

public class SessionBindingFactory implements BindingFactory {
	private final ApplicationStateManager state;
	private final PropertyConduitSource source;

	public SessionBindingFactory(ApplicationStateManager state, PropertyConduitSource source) {
		this.state = state;
		this.source = source;
	}

	public Binding newBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location) {
		try {
			Integer index = expression.lastIndexOf(".");
			String className = expression.substring(0, index);
			String propertyExpression = expression.substring(index+1);
			Class<?> stateClass = Class.forName(className);
	        PropertyConduit conduit = source.create(stateClass, propertyExpression);
	
	        //StringInterner is internal
	        String toString = String.format("SessionBinding[%s %s(%s)]", description, container
	                .getCompleteId(), expression);
	        return new SessionBinding(location,conduit,toString,state,stateClass);
		} catch (Exception ex) {
			throw new TapestryException(ex.getMessage(), location, ex);
		}
	}

}
