package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PropertyEditContext;

public class AppPropertyEditBlocks {
	
	@Property
    @Environmental
    private PropertyEditContext context;
	
	@Component(parameters =
	    { "value=context.propertyValue", "label=prop:context.label",
	            "translate=prop:imageTranslator", "validate=prop:imageValidator",
	            "clientId=prop:context.propertyId", "annotationProvider=context" })
	    private TextField image;

	    @Inject
	    private ComponentResources resources;

	    public FieldValidator getImageValidator()
	    {
	      return context.getValidator(image);
	    }
	    
	    public FieldTranslator getImageTranslator()
	    {
	      return context.getTranslator(image);
	    }

}
