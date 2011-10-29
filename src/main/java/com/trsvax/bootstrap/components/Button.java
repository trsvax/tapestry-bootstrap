package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.ButtonEnvironment;

public class Button {
	
	@Parameter(value="prop:button?.type",defaultPrefix="literal")
	private String type;
	
	@Parameter(value="prop:button?.size",defaultPrefix="literal")
	private String size;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private ButtonEnvironment button;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("button", "class", String.format("btn%s%s", format(size) , format(type)));
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
	String format(String s) {
		return s == null ? "" : " " + s;
	}

}
