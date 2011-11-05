package com.trsvax.bootstrap.services;

import java.lang.reflect.Method;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.services.Environment;

public class EnvironmentBinding extends AbstractBinding {
	Class<?> enviromentInterface;
	private final Method get;
	private final Method set;
	private final Environment environment;

	public EnvironmentBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location,
			Environment environment) throws Exception {
		
		Integer index = expression.lastIndexOf(".");
		String className = expression.substring(0, index);
		enviromentInterface = Class.forName(className);
		String propertyName = expression.substring(index+1);
		get = findMethod("get",enviromentInterface, propertyName);	
		set = findMethod("set",enviromentInterface, propertyName);
		this.environment = environment;
	}

	public Object get() {
		try {
			return get.invoke(environment.peek(enviromentInterface));
		} catch (Exception e) {
		}
		return null;
	}
	
	public void set(Object value) {
		try {
			set.invoke(environment.peek(enviromentInterface), value);
		} catch (Exception e) {
		}
	}

	private Method findMethod(String type,Class<?> ssoClass,String propertyName) {
		String methodName = type + propertyName.toLowerCase();
		for ( Method m : ssoClass.getMethods() ) {
			if ( m.getName().toLowerCase().equals(methodName)) {
				return m;
			}
		}
		return null;
	}

}
