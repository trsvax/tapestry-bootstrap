package com.trsvax.bootstrap.services;

import org.apache.tapestry5.BaseValidationDecorator;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;


public class BootStrapValidationDecorator extends BaseValidationDecorator {
	private final MarkupWriter markupWriter;
	 private final Environment environment;
	 private final Logger logger;
	
	public BootStrapValidationDecorator(MarkupWriter markupWriter,Environment environment, Logger logger) {
		this.markupWriter = markupWriter;
		this.environment = environment;
		this.logger = logger;
	}
	
	public void beforeField(Field field) {
		if ( inError( field))  {
			logger.info("{}",field.getLabel());
			markupWriter.getElement().getContainer().addClassName("error");
		}
	}
	
	public void insideField(Field field)
    {
        if (inError(field)) {
        	markupWriter.getElement().addClassName("error");
        }
        	
    }
	
	public void afterField(Field field) {
		if ( inError(field)) {
			Element e = markupWriter.element("span","class","help-inline");
			markupWriter.write(getError(field));
			markupWriter.end();
		}
	}
	
	 private boolean inError(Field field)
	    {
	        ValidationTracker tracker = environment.peekRequired(ValidationTracker.class);

	        return tracker.inError(field);
	    }
	 
	 private String getError(Field field)
	    {
	        ValidationTracker tracker = environment.peekRequired(ValidationTracker.class);

	        return tracker.getError(field);
	    }

}
