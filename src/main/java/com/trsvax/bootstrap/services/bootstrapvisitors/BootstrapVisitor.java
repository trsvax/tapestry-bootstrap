package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.Map;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.slf4j.Logger;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkVisitor;


public class BootstrapVisitor  implements FrameworkVisitor {
	public static final String id = "FrameworkVisitor";
	private final Logger logger;
	private final Map<String,FrameworkVisitor> visitors;
	private final String namespace = "fw";
	
	public BootstrapVisitor(Logger logger, Map<String, FrameworkVisitor> configuration) {
		this.logger = logger;
		this.visitors = configuration;
		for ( String name : visitors.keySet() ) {
			logger.info("visitor: {}",name);
		}
	}

	public Visitor visit() {
		return new Visitor() {			
			public void visit(Element element) {
				if ( isNameSpace(element,namespace)) {
					FrameworkVisitor visitor = getVisitor(element.getName());
					element.visit(visitor.visit());				
				}
			}
		};
	}
	

	public void beginRender(FrameworkMixin component, MarkupWriter writer) {
		getVisitor(namespace + "." + component.getClass().getSimpleName()).beginRender(component, writer);
	}

	public void afterRender(FrameworkMixin component,MarkupWriter writer) {
		getVisitor(namespace + "." + component.getClass().getSimpleName()).afterRender(component, writer);
	}
	
	FrameworkVisitor getVisitor(String name) {
		FrameworkVisitor visitor = visitors.get("fw");
		if ( visitor == null ) {
			throw new RuntimeException(String.format("ns: (%s) name: (%s)", namespace, name));
		} 
		return visitor;
	}
	
	public String getID() {
		return id;
	}
	
	boolean isNameSpace(Element element, String namespace) {
		if ( element.getNamespace() == null ) {
			return false;
		}
		if ( element.getNamespace().equals(namespace) ) {
			return true;
		}
		return false;
	}

}
