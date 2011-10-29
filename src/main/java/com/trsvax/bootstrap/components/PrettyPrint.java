package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.PrettyPrintEnvironment;

@Import(stylesheet="classpath:/com/trsvax/bootstrap/google-code-prettify/prettify.css",
library="classpath:/com/trsvax/bootstrap/google-code-prettify/prettify.js")
public class PrettyPrint {
	@Parameter(value="prop:prettyPrint?.language",defaultPrefix="literal")
	private String language;
	
	@SuppressWarnings("unused")
	@Environmental(false)
	@Property
	private PrettyPrintEnvironment prettyPrint;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("pre", "class", String.format("prettyprint%s",format(language)));
	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
	String format(String s) {
		return s == null ? "" : " " + s;
	}
}
