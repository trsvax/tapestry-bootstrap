package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class Label {
	
	@Parameter(value="",defaultPrefix="literal")
	@Property
	private String type;
	
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("span","class","label " + type);
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
