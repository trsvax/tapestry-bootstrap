package com.trsvax.bootstrap.mixins;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.testng.annotations.Parameters;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkVisitor;

//@SupportsInformalParameters
public class FW implements FrameworkMixin {
	
	@Inject
	@Service("FrameworkVisitor")
	private FrameworkVisitor vistor;

	@Inject
	private ComponentResources componentResources;
	
	@Parameter(defaultPrefix="literal")
	private String type;
	
	@Parameter(defaultPrefix="literal")
	private String projectName;
	
	@Parameter(defaultPrefix="literal")
	private String sortable;
	
	
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
	
	public Map<String,String> getParms() {
		Map<String,String> parms = new HashMap<String, String>();
		
		parms.put("type", type);
		parms.put("projectName", projectName);
		parms.put("sortable", sortable);
		return parms;
	}

}
