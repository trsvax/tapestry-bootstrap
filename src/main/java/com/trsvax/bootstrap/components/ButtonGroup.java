package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;
import com.trsvax.bootstrap.environment.ButtonGroupValues;

@SupportsInformalParameters
public class ButtonGroup {
	
 	@Inject
  	private Environment environment;
 	
 	@Inject
 	private ComponentResources resources;
	
	@SetupRender
	void setupRender() {
		environment.push(ButtonGroupEnvironment.class, new ButtonGroupValues(null).withButtonGroup(true));
	}
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div", "class","btn-group");
		resources.renderInformalParameters(writer);
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
	@CleanupRender
	void cleanupRender() {
		environment.pop(ButtonGroupEnvironment.class);
	}

}
