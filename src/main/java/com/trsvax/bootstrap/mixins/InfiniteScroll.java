package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.PagerEnvironment;

@Import(library="classpath:META-INF/assets/module/trsvax/infinitescroll/jquery.infinitescroll.js")
@MixinAfter
public class InfiniteScroll  {
	
	@Parameter(defaultPrefix="literal",name="data-infiniteScrollSpec")
	private JSONObject spec;
	
	@Parameter(value=PagerEnvironment.nextURL,required=true,allowNull=false)
	private String nextURL;
	
	@Parameter(value=PagerEnvironment.id,required=true,allowNull=false)
	private String id;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	private Logger logger;
	
	@CleanupRender
	void cleanupRender() {

		if ( spec == null ) {
			spec = new JSONObject();
			spec.accumulate("debug",true);
			spec.accumulate("navSelector", String.format("div.pagination"));
			spec.accumulate("nextSelector", "div.pagination .next a");
			spec.accumulate("itemSelector", String.format("#%s li",id));
		}
		javaScriptSupport.require("trsvax/bootstrap").invoke("infinitescroll").with(id,nextURL,spec);
	}

}
