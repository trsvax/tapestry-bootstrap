package com.trsvax.bootstrap.components;

import com.trsvax.bootstrap.environment.AlertType;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.AlertEnvironment;

/**
 * Display a <a href="http://twitter.github.com/bootstrap/#alerts">Bootstrap alert</a>.
 * @tapestrydoc
 */
@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BasicAlert extends BootstrapComponent {

    /** Type of alert: any value of the {@link AlertType} enum, that is <tt>info</tt>, <tt>warning</tt> or <tt>error</tt> */
	@Parameter(value=AlertEnvironment.type,defaultPrefix="literal")
	private String type;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("div", "class", String.format("alert-message%s", formatClass(type)),"data-alert","alert");
		writer.element("a", "class","close","href","#");
		writer.write("x");
		writer.end();
		writer.element("p");
	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
		writer.end();
	}
	

}
