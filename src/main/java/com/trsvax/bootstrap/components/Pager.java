package com.trsvax.bootstrap.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ValueEncoderSource;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.PagerEnvironment;
import com.trsvax.bootstrap.environment.PagerValues;

public class Pager {

	@Parameter(name = "for", required = true, allowNull = false, defaultPrefix = BindingConstants.LITERAL)
	private String component;
	
	@Parameter
	private Integer currentPage;

	@Parameter(defaultPrefix="literal")
	private Integer rowsPerPage;

	@Parameter(defaultPrefix="prop")
	private Integer availableRows;

	@Inject
	private Environment environment;

	@Inject
	private Request request;

	@Inject
	private ValueEncoderSource valueEncoderSource;

	@Inject
	private ComponentResources resources;


	@Inject
	private Logger logger;


	@SetupRender
	void setupRender()  {	
		environment.push(PagerEnvironment.class, new PagerValues(rowsPerPage,currentPage(),availableRows,component) );
	}

	@CleanupRender
	void cleanupRender() {
		environment.pop(PagerEnvironment.class);
	}
	
	public String getElement() {
		return "div";
	}
	

	private Integer currentPage() {
		if ( currentPage != null ) {
			return currentPage;
		}
		if ( request.getParameter(component) == null ) {
			return 1;
		}
		return valueEncoderSource.getValueEncoder(Integer.class).toValue(request.getParameter(component));
	}

}
