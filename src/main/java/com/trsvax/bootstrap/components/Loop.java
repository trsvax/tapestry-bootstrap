package com.trsvax.bootstrap.components;

import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.LoopEnvironment;

@SuppressWarnings("unused")
public class Loop<T> {	
	@Parameter(value="values?.source",required=true)
	@Property
	private List<T> source;
	
	@Parameter
	@Property
	private Object value;
	
	@Parameter
	@Property
	private Integer index;
	
	@Environmental(false)
	@Property
	private LoopEnvironment<T> values;

	@Component(parameters={"source=source","value=value","index=index"})
	private org.apache.tapestry5.corelib.components.Loop<T> loop;
}
