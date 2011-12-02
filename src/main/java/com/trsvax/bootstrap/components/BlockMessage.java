package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.BlockMessageEnvironment;


/**
 * @tapestrydoc
 */
@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BlockMessage extends BootstrapComponent {
	@Parameter(value=BlockMessageEnvironment.type,defaultPrefix="literal")
	private String type;
	
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private Block actions;
	
	public String getType() {
		return formatClass(type);
	}	
}
