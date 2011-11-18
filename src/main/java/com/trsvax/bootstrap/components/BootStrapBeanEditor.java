package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.corelib.components.BeanEditor;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.FormSupport;

public class BootStrapBeanEditor extends BeanEditor {
	
	@Inject
	private ComponentResources resources;
	
	@Environmental
	private ValidationTracker validationTracker;
	
	@Environmental
	private FormSupport formSupport;
	
	
	
	public String getLegend() {
		String legend =  getObject().getClass().getSimpleName();
		Messages m =  resources.getPage().getComponentResources().getMessages();
		if ( m.contains(legend + "-legend")) {
			return m.get(legend + "-legend");
		}
		return legend;
	}
	
	
	 
}
