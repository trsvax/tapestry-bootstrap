package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;

@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BasicAlert {
	
	@Parameter(value="",defaultPrefix="literal")
	private String type;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div", "class","alert-message " + type);
		writer.element("a", "class","close","href","#");
		writer.write("x");
		writer.end();
		writer.element("p");
	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
		writer.end();
	}
}
