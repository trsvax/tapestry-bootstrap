package com.trsvax.bootstrap.services;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.FrameworkEnvironment;

public class ExcludeVisitorImpl implements ExcludeVisitor {
	private final Logger logger;
	
	public ExcludeVisitorImpl(Logger logger) {
		this.logger = logger;
	}
	
	public Visitor visit(final FrameworkEnvironment values) {
		return new Visitor() {					
			public void visit(Element element) {
				if ( element.getName().equals("link") ) {
					String type = element.getAttribute("type");
					String href = element.getAttribute("href");
					if ( type != null && href != null && type.equals("text/css")) {
						for ( String pattern : values.getExcludes()) {
							if ( href.contains(pattern)) {
								//logger.info("remove {}",element);
								element.remove();
								
							}
						}
					}
				}
				
			}
		};
	}

}
