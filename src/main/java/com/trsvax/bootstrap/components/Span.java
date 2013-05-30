package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;

import com.trsvax.bootstrap.environment.RowEnvironment;

public class Span {
	@Parameter(required=true)
	private Integer span;
	
	@Parameter
	private Integer offset;
	
	@Environmental
	  private RowEnvironment rowEnvironment;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		Element tag = writer.element("div");
		if ( offset != null ) {
			tag.attribute("class", String.format("span%d offset%d", span,offset));
		} else {
			tag.attribute("class", String.format("span%d", span));

		}
		rowEnvironment.addOffset(offset);
		rowEnvironment.addSpan(span);
		
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
