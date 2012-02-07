package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkVisitor;

@SupportsInformalParameters
public class FW implements FrameworkMixin {
	
	@Inject
	@Service("FrameworkVisitor")
	private FrameworkVisitor vistor;

	@Inject
	private ComponentResources componentResources;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		vistor.beginRender(this, writer);
	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
			vistor.afterRender(this, writer);
	}

	public ComponentResources getComponentResources() {
		return componentResources;
	}

}
