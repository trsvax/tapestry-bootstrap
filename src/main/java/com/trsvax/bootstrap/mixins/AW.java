package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

@SupportsInformalParameters
@MixinAfter
public class AW {
	
	@Inject
	private ComponentResources resources;
	
	@BeginRender
	public void beginRender(MarkupWriter writer) {
		resources.renderInformalParameters(writer);
	}
}
