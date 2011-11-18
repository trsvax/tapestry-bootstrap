package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.corelib.pages.PropertyEditBlocks;
import org.apache.tapestry5.services.PropertyEditContext;

public class BootStrapEditBlocks extends PropertyEditBlocks {
	
	@Environmental
    private PropertyEditContext context;
		
	public String getCss() {
		String id = context.getPropertyId() + "-size";
		
		if ( context.getContainerMessages().contains(id) ) {
			return context.getContainerMessages().get(id);
		}
		return " ";
	}

}
