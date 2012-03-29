package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;


public class DropDown  {
	
 	@Inject
  	private Environment environment;
 	
 	private ButtonGroupEnvironment buttonGroupEnvironment;
	
	@SetupRender
	void setupRender() {
		buttonGroupEnvironment = environment.peek(ButtonGroupEnvironment.class);
		if ( buttonGroupEnvironment != null ) {
			buttonGroupEnvironment.setDropDown(true);
		}
	}
	
	@CleanupRender
	void cleanupRender() {
		if ( buttonGroupEnvironment != null ) {
			buttonGroupEnvironment.setDropDown(false);
		};
	}

}
