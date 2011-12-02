package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.AlertEnvironment;

/**
 * @tapestrydoc
 */
@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BasicAlert extends BootstrapComponent {
	
	@Parameter(value=AlertEnvironment.type,defaultPrefix="literal")
	private String type;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div", "class", String.format("alert-message%s", formatClass(type)));
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
