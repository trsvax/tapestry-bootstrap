package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.ButtonEnvironment;
import com.trsvax.bootstrap.environment.LabelEnvironment;

public class Label {
	
	@Parameter(value="prop:label?.type",defaultPrefix="literal")
	private String type;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private LabelEnvironment label;
		
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("span","class", String.format("label%s",format(type)));
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
	String format(String s) {
		return s == null ? "" : " " + s;
	}
}
