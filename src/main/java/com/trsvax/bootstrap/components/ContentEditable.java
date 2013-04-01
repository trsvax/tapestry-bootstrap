package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(
library={
		"classpath:/com/trsvax/bootstrap/assets/components/ckeditor/ckeditor.js"
		}
)
@SupportsInformalParameters
public class ContentEditable {
	
	@Parameter
	private boolean enable;
	
	@Parameter(defaultPrefix="literal")
	private String event;
	
	@Parameter
	private Object[] context;
	
	@Inject
	private ComponentResources resources;
	
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		Element div = writer.element("div");
		if ( enable ) {
			Link link = resources.getContainerResources().createEventLink(event, context);
			div.attribute("data-url",link.toAbsoluteURI());
			div.attribute("contenteditable", "true");
		}
		resources.renderInformalParameters(writer);		
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
