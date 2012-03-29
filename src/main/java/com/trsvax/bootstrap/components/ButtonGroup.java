package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;
import com.trsvax.bootstrap.environment.ButtonGroupValues;

public class ButtonGroup {
	
 	@Inject
  	private Environment environment;
	
	@SetupRender
	void setupRender() {
		environment.push(ButtonGroupEnvironment.class, new ButtonGroupValues(null).withButtonGroup(true));
	}
	
	@CleanupRender
	void cleanupRender() {
		environment.pop(ButtonGroupEnvironment.class);
	}

}
