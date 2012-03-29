package com.trsvax.bootstrap.mixins;

import java.util.Collections;
import java.util.Map;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkProvider;
import com.trsvax.bootstrap.FrameworkVisitor;
import com.trsvax.bootstrap.environment.FrameWorkEnvironment;

//@SupportsInformalParameters
//@MixinAfter
public class FW implements FrameworkMixin {
		
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String fw;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String fwtype;
	
	@Parameter
	private Map<String,Object> fwargs;
	
	@Inject
	@Service("FrameworkVisitor")
	private FrameworkVisitor vistor;

	@Inject
	private ComponentResources componentResources;
	
	@Inject
	@Service("FrameworkProvider")
	private FrameworkProvider frameworkProvider;
	
	@Inject
	private Environment environment;
	
	private Element root;
	private boolean instrument;
	
	@SetupRender
	void setupRender(MarkupWriter writer) {
		root = null;

		FrameWorkEnvironment frameWorkEnvironment = environment.peek(FrameWorkEnvironment.class);
		if ( frameWorkEnvironment != null && fw == null ) {
			fw = frameWorkEnvironment.getName();
		}
		if ( fw == null ) {
			fw = "";
		}
		frameworkProvider.setupRender(this, writer);

	}
	
		
	@BeforeRenderBody
	void beginRender(MarkupWriter writer) {
		//vistor.beginRender(this, writer);
	}

	@CleanupRender
	void cleanupRender(MarkupWriter writer) {
			frameworkProvider.cleanupRender(this, writer); 
			//vistor.afterRender(this, writer);
	}


	public String getFW() {
		return fw;
	}
	
	public String getType() {
		return fwtype;
	}
	
	public Map<String,Object> getParms() {
		return (Map<String, Object>) (fwargs == null ? Collections.emptyMap() : fwargs);
	}
	
	public ComponentResources getComponentResources() {
		return componentResources;
	}


	public Element getRoot() {
		return root;
	}
	public void setRoot(Element element) {
		this.root = element;
	}


	public String getComponentClassName() {
		return componentResources.getContainer().getClass().getCanonicalName();
	}
	


}
