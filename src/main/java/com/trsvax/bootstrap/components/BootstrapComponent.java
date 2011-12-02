package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @tapestrydoc
 */
public abstract class BootstrapComponent implements ClientElement {
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	private String clientId;
	
	public String getClientId() {
		if ( clientId == null ) {
			clientId = javaScriptSupport.allocateClientId(resources);
		}
		return clientId;
	}
	
	String formatClass(String s) {
		return s == null ? "" : " " + s;
	}

}
