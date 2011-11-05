package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.LabelEnvironment;

public class Label extends BootstrapComponent {
	
	@Parameter(value=LabelEnvironment.type,defaultPrefix="literal")
	private String type;
	
		
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("span","class", String.format("label%s",formatClass(type)));
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
}
