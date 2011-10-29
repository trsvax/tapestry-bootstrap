package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.BlockMessageEnvironment;


@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BlockMessage {
	@Parameter(value="prop:blockMessage?.type",defaultPrefix="literal")
	private String type;
	
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private Block actions;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private BlockMessageEnvironment blockMessage;
	
	public String getType() {
		return format(type);
	}
	
	String format(String s) {
		return s == null ? "" : " " + s;
	}
}
