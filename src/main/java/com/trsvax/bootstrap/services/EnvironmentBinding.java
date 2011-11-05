package com.trsvax.bootstrap.services;

import java.lang.annotation.Annotation;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.internal.util.TapestryException;
import org.apache.tapestry5.services.Environment;

public class EnvironmentBinding implements Binding {
	private final Class<?> enviromentInterface;
	private final Environment environment;
	private final PropertyConduit conduit;
	private final String toString;
	private final Location location;

	public EnvironmentBinding(Location location,
			PropertyConduit conduit, String toString, Environment environment, Class<?> enviromentInterface) {
		this.environment = environment;
		this.toString = toString;
		this.enviromentInterface = enviromentInterface;
		this.conduit = conduit;
		this.location = location;
	}

	public Object get() {
		try {
			Object o = environment.peek(enviromentInterface);
			if ( o != null ) {
				return conduit.get(o);
			}
		} catch (Exception ex) {
			throw new TapestryException(ex.getMessage(), location, ex);
		}
		return null;
	}
	
	public void set(Object value) {
		try {
			Object o = environment.peek(enviromentInterface);
			if ( o != null ) {
				conduit.set(o, value);
			}
		} catch (Exception ex) {
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
