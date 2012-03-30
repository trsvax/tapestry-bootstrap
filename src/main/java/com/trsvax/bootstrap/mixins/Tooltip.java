package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@MixinAfter
public class Tooltip {
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@BeginRender
	void beginRender() {
		//just blow up if not a clientElement
		String id = ((ClientElement) resources.getContainer()).getClientId();
		//TODO fix me
		javaScriptSupport.addScript("$('#%s').tooltip()", id);
	}

}
