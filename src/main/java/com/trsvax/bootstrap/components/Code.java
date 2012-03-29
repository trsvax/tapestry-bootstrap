package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.FrameWorkEnvironment;

@Import(
library={
		"classpath:/com/trsvax/bootstrap/assets/components/code/google-code-prettify/prettify.js"
		}
)
public class Code {
	
	@Inject
	private Environment environment;
	
	@AfterRender
	void afterRender() {
		FrameWorkEnvironment frameWorkEnvironment = environment.peek(FrameWorkEnvironment.class);
		frameWorkEnvironment.addScriptOnce("prettyPrint();");
	}

}
