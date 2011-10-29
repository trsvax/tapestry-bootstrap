package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.AlertEnvironment;

@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BasicAlert {
	
	@Parameter(value="prop:alert?.type",defaultPrefix="literal")
	private String type;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private AlertEnvironment alert;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div", "class", String.format("alert-message%s", format(type)));
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
	
	String format(String s) {
		return s == null ? "" : " " + s;
	}
}
