package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.ButtonEnvironment;

public class Label {
	
	@Parameter(value="label?.type",defaultPrefix="literal")
	@Property
	private String type;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private ButtonEnvironment label;
	
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("span","class", String.format("label %s",type == null ? "" : type));
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
