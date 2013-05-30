package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;

public class Container {

	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div","class","container-fluid");		
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
}
