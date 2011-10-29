package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

public class Button {
	
	@Parameter(value="")
	private String type;
	
	@Parameter(value="")
	private String size;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("button", "class", size + " " + type);
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
