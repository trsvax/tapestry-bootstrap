package com.trsvax.bootstrap.components;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class MediaGrid<T> {
	
	@Parameter(autoconnect=true,required=true)
	@Property
	private List<?> source;
	
	@Parameter
	@Property
	private Object value;
	
	@Parameter(value="resources.id")
	@Property
	private String parameterName;
	
	@Inject
	@Property
	private ComponentResources resources;

}
