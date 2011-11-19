package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class BeanEditor extends org.apache.tapestry5.corelib.components.BeanEditor {
	
	@Inject
	private ComponentResources resources;
	
	
	public String getLegend() {
		String legend =  getObject().getClass().getSimpleName();
		Messages m =  resources.getPage().getComponentResources().getMessages();
		if ( m.contains(legend + "-legend")) {
			return m.get(legend + "-legend");
		}
		return legend;
	}
	
	
	 
}
