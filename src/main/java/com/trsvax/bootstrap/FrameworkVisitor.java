package com.trsvax.bootstrap;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Visitor;

public interface FrameworkVisitor {
	
	void beginRender(FrameworkMixin component, MarkupWriter writer);
	void afterRender(FrameworkMixin component, MarkupWriter writer);

	Visitor visit();

}
