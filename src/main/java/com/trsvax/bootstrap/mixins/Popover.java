package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@MixinAfter
public class Popover {
	
	@Parameter(defaultPrefix="literal",name="data-popoverSpec")
	private JSONObject spec;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@BeginRender
	void beginRender() {
		//just blow up if not a clientElement
		String id = ((ClientElement) resources.getContainer()).getClientId();
		if ( spec == null ) {
			spec = new JSONObject();
		}
		//args.accumulate("placement", "top");
		javaScriptSupport.require("trsvax/bootstrap").invoke("popover").with(id,spec);
	}

}
