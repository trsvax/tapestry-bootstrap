package com.trsvax.bootstrap.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class Modal implements ClientElement {
	@Parameter(value = "prop:componentResources.id", defaultPrefix = BindingConstants.LITERAL)
	private String clientId;
	
	@Parameter
	@Property
	private Block header;
	
	@Parameter
	@Property
	private Block footer;
	
	@Parameter
	private boolean fade;
	
	@Inject
	private JavaScriptSupport javascriptSupport;
	
	 @Inject
	 private ComponentResources resources;
	
	private String uniqueId;
	
	private Element modalElement;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		modalElement = writer.element("div", "class","modal ");
		if ( fade ) {
			modalElement.addClassName("fade");
		}
		uniqueId = null;
		if ( clientId != null ) {
			getClientId();
		}

	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}
	
	public String getClientId() {	
		if (modalElement == null) {
				            throw new IllegalStateException(String.format(
				                    "Unable to provide client id for component %s as it has not yet rendered.", resources
				                            .getCompleteId()));
		}
				
			
        if (uniqueId == null)
        {
            uniqueId = javascriptSupport.allocateClientId(clientId);
            modalElement.forceAttributes("id", uniqueId);
        }

        return uniqueId;
    }

}
