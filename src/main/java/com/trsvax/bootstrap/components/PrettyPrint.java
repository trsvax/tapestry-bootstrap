package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;

@Import(stylesheet="classpath:/com/trsvax/bootstrap/google-code-prettify/prettify.css",
library="classpath:/com/trsvax/bootstrap/google-code-prettify/prettify.js")
public class PrettyPrint {
	@Parameter(value="")
	private String language;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("pre", "class","prettyprint " + language);
	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
}
