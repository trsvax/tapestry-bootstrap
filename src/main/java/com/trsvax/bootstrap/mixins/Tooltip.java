package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

@MixinAfter
public class Tooltip {
	@Parameter(defaultPrefix="literal",name="data-tooltipSpec")
	private JSONObject spec;
	
	@Parameter(defaultPrefix="literal")
	private String title;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	private Logger logger;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		//just blow up if not a clientElement
		String id = ((ClientElement) resources.getContainer()).getClientId();
		
		logger.info("args {}",resources.getContainerResources().getInformalParameterNames());
		
		if ( title != null ) {
			writer.getElement().attribute("title", title);
		}
		
		if ( spec == null && title == null ) {
			return;
		}
		
		if ( spec == null ) {
			spec = new JSONObject();
		}
		
		javaScriptSupport.require("trsvax/bootstrap").invoke("tooltip").with(id,spec);
	}

	
}
