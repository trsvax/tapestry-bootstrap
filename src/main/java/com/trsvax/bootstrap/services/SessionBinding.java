package com.trsvax.bootstrap.services;

import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.internal.util.TapestryException;
import org.apache.tapestry5.services.ApplicationStateManager;

public class SessionBinding extends AbstractBinding {
	private final ApplicationStateManager state;
	private final Class<?> ssoClass;
	private PropertyConduit conduit;
	private final String toString;
	 
	
	public SessionBinding(Location location, PropertyConduit conduit,
			String toString, ApplicationStateManager state, Class<?> stateClass) {
		this.state = state;
		this.ssoClass = stateClass;
		this.conduit = conduit;
		this.toString = toString;
	}

	public Object get() {
		try {
			return conduit.get(state.get(ssoClass));
		} catch (Exception ex) {
			throw new TapestryException(ex.getMessage(), getLocation(), ex);
		}
	}
	
	public void set(Object value) {
		try {
			conduit.set(state.get(ssoClass), value);
		} catch (Exception ex)  {
			throw new TapestryException(ex.getMessage(), getLocation(), ex);
		}
	}
	
	public boolean isInvariant() {
		return false;
	}
	
	public String toString() {
		return toString;
	}
	

}
