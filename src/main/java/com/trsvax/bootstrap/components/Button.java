package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.ButtonEnvironment;

public class Button extends BootstrapComponent {
	
	@Parameter(value=ButtonEnvironment.type,defaultPrefix="literal")
	private String type;
	
	@Parameter(value=ButtonEnvironment.size,defaultPrefix="literal")
	private String size;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("button", "class", String.format("btn%s%s", formatClass(size) , formatClass(type)));
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
}
