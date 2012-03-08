package com.trsvax.bootstrap.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.BootstrapEnvironment;

public class ExcludeVisitorImpl implements ExcludeVisitor {
	private final Logger logger;
	private final String mode;
	
	public ExcludeVisitorImpl(Logger logger, @Symbol(SymbolConstants.EXECUTION_MODE) String mode) {
		this.logger = logger;
		this.mode = mode;
	}
	
	public Visitor visit(final BootstrapEnvironment values) {
		return new Visitor() {					
			public void visit(Element element) {
				if ( element.getName().equals("link") ) {
					String type = element.getAttribute("type");
					String href = element.getAttribute("href");
					if ( type != null && href != null && type.equals("text/css")) {
						for ( String pattern : values.getExcludes(mode)) {
							if ( href.contains(pattern)) {
								element.remove();
								
							}
						}
					}
				}
				
			}
		};
	}

}
