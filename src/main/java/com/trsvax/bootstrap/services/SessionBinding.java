package com.trsvax.bootstrap.services;

import java.lang.reflect.Method;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.slf4j.Logger;

public class SessionBinding extends AbstractBinding {
	private final Logger logger;
	private final ApplicationStateManager state;
	private final Method get;
	private final Method set;
	private final Class<?> ssoClass;
	 
	
	public SessionBinding(String description, ComponentResources container, 
			ComponentResources component, String expression, Location location, 
			ApplicationStateManager state, Logger logger) throws Exception {
		this.logger = logger;
		this.state = state;
		Integer index = expression.lastIndexOf(".");
		String className = expression.substring(0, index);
		ssoClass = Class.forName(className);
		String propertyName = expression.substring(index+1);
		get = findMethod("get",ssoClass, propertyName);	
		set = findMethod("set",ssoClass, propertyName);
	}

	public Object get() {
		try {
			return get.invoke(state.get(ssoClass));
		} catch (Exception e) {
			logger.error("{}",e.getMessage());
		}
		return null;
	}
	
	public void set(Object value) {
		try {
			set.invoke(state.get(ssoClass), value);
		} catch (Exception e)  {
			logger.error("{}",e.getMessage());
		}
	}
	
	public boolean isInvariant() {
		return false;
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
