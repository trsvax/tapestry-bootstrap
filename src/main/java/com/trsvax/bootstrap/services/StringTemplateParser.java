package com.trsvax.bootstrap.services;

import org.apache.tapestry5.services.dynamic.DynamicTemplate;

public interface StringTemplateParser {
	
	public DynamicTemplate parse(String template);

}
