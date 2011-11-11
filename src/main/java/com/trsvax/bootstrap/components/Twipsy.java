package com.trsvax.bootstrap.components;

import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

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
	

	@BeginRender
	void beginRender() {
		if ( getTitleIsString() ) {
			//javaScriptSupport.addScript("$('a[rel=twipsy]').twipsy({live: true})");
			environment.addScriptOnce("$('a[rel=twipsy]').twipsy({live: true})");
		} else {
			// Will this work in a zone?
			javaScriptSupport.addScript(
					"$('#%s').twipsy({html: true, " +
					"title:function() {return $('#%s span').html()}})", getClientId(), getClientId());
		}
	}
	
	public boolean getTitleIsString() {
		return title instanceof String;
	}

	
}
