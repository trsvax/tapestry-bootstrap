package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.LoopEnvironment;

public class Loop<T> {
	
	@Environmental
	@Property
	private LoopEnvironment<T> values;
	
	@Parameter
	@Property
	private Object value;

	@Component(parameters={"source=values.source","value=value"})
	private org.apache.tapestry5.corelib.components.Loop<T> loop;
}
