package com.trsvax.bootstrap.mixins;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkVisitor;

//@SupportsInformalParameters
public class FW implements FrameworkMixin {
	
	@Inject
	@Service("FrameworkVisitor")
	private FrameworkVisitor vistor;

	@Inject
	private ComponentResources componentResources;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String type;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String projectName;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String sortable;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String buttons;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String fwtype;
	
	
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
		
		parms.put("fwtype", fwtype);
		parms.put("type", type);
		parms.put("projectName", projectName);
		parms.put("sortable", sortable);
		parms.put("buttons",buttons);
		return parms;
	}

}
