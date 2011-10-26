package com.trsvax.bootstrap.components;

import java.util.List;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class MediaGrid {
	
	@Parameter
	@Property
	private List<?> source;
	
	@Parameter
	@Property
	private Object row;

}
