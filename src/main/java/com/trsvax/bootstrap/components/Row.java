package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.RowEnvironment;
import com.trsvax.bootstrap.environment.RowEnvironmentImpl;

public class Row {
	
	 @Inject
	  private Environment environment;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		environment.push(RowEnvironment.class, new RowEnvironmentImpl());
		writer.element("div", "class","row-fluid");
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
		environment.pop(RowEnvironment.class);
	}

}
