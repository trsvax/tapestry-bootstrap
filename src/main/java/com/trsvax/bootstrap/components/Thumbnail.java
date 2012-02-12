package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

@SupportsInformalParameters
public class Thumbnail {

	@Inject
	private ComponentResources resources;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		resources.renderInformalParameters(writer);
	}
}
