package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;


@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BlockMessage {
	@Parameter(value="",defaultPrefix="literal")
	@Property
	private String type;
	
	@Parameter
	@Property
	private Block actions;
	
	
}
