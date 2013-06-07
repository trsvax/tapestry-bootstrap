package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;


public class Layout  {
	
	@Parameter
	@Property
	private Block subnav;
	
	@Parameter(defaultPrefix="literal")
	@Property
	private String title;

}

