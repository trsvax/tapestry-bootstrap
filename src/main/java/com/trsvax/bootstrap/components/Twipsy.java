package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

import com.trsvax.bootstrap.environment.ExcludeEnvironment;

@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-twipsy.js")
public class Twipsy extends BootstrapComponent {
	@Parameter(defaultPrefix="literal")
	@Property
	private Object title;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Environmental
	private ExcludeEnvironment environment;
	
	@Inject
	@Symbol(JQuerySymbolConstants.JQUERY_ALIAS)
	private String jqueryAlias;
	

	@BeginRender
	void beginRender() {
		if ( getTitleIsString() ) {
			environment.addScriptOnce(String.format("%s('a[rel=twipsy]').twipsy({live: true})",jqueryAlias));
		} else {
			// Will this work in a zone?
			javaScriptSupport.addScript(
					"%s('#%s').twipsy({html: true, " +
					"title:function() {return %s('#%s span').html()}})", jqueryAlias, getClientId(),jqueryAlias, getClientId());
		}
	}
	
	public boolean getTitleIsString() {
		return title instanceof String;
	}

	
}
