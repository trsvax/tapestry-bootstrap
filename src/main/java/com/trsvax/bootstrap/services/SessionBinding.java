package com.trsvax.bootstrap.services;

import java.lang.annotation.Annotation;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.internal.util.TapestryException;
import org.apache.tapestry5.services.ApplicationStateManager;

public class SessionBinding implements Binding {
	private final ApplicationStateManager state;
	private final Class<?> ssoClass;
	private PropertyConduit conduit;
	private final String toString;
	private final Location location;
	 
	
	public SessionBinding(Location location, PropertyConduit conduit,
			String toString, ApplicationStateManager state, Class<?> stateClass) {
		this.state = state;
		this.ssoClass = stateClass;
		this.conduit = conduit;
		this.toString = toString;
		this.location = location;
	}

	public Object get() {
		try {
			return conduit.get(state.get(ssoClass));
		} catch (Exception ex) {
			throw new TapestryException(ex.getMessage(), location, ex);
		}
	}
	
	public void set(Object value) {
		try {
			conduit.set(state.get(ssoClass), value);
		} catch (Exception ex)  {
			throw new TapestryException(ex.getMessage(), location, ex);
		}
	}
	
	public boolean isInvariant() {
		return false;
	}
	
	public String toString() {
		return toString;
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		return conduit.getAnnotation(annotationClass);
	}

	public Class<?> getBindingType() {
		return conduit.getPropertyType();
	}
	

}
