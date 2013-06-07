package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.annotations.BindParameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.LoopEnvironment;

public class LoopEnvironmentMixin<T> {
	
	@BindParameter
	private Iterable<T> source;

 	@Inject
  	private Environment environment;
	
	@SetupRender
	void setupRender() {
		LoopEnvironment<T> env = environment.peek(LoopEnvironment.class);
		if ( env != null && ! env.isBound() ) {
			source = env.bindSource();
		}
	}

}
