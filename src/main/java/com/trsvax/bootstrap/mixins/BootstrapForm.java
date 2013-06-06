package com.trsvax.bootstrap.mixins;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

@MixinAfter
public class BootstrapForm {
	
	private Element form;
	
	@Inject
	private Logger logger;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		form = writer.getElement();
	}
	
	private List<Element> divs;
	private boolean broken;
	
	@CleanupRender
	void cleanupRender() {
		String formClass = form.getAttribute("class");
		if ( formClass != null && formClass.contains("form-horizontal")) {
			return;
		}
		divs = new ArrayList<Element>();
		form.visit( new Visitor() {
			
			public void visit(Element element) {
				String className = element.getAttribute("class");
				if ( element.getName().equals("div") && (isControl(className) || isFormActions(className)) ) {
					divs.add(element);
				}			
				if ( className != null && className.contains("control-label")) {
					String labelClass = className.replace("control-label", "");
					if ( labelClass.length() == 0 ) {
						element.forceAttributes("class",null);
					} else {
						element.forceAttributes("class",labelClass);
					}
				}
				if ( element.getName().equals("div") && isButtonGroup(className) && ! broken) {
					element.elementBefore("br");
					broken = true;
				}
				
				
			}
		});
		for ( Element div : divs ) {
			div.pop();
		}
		List<Node> children = form.getChildren();
		Element fieldset = form.element("fieldset");
		for ( Node child : children ) {
			child.moveToBottom(fieldset);
		}
		
	}
	
	private boolean isButtonGroup(String className) {
		if ( className == null ) {
			return false;
		}
		if ( className.contains("btn-group")) {
			return true;
		}
		return false;
	}
	
	private boolean isFormActions(String className) {
		if ( className == null ) {
			return false;
		}
		if ( className.contains("form-actions")) {
			return true;
		}
		return false;
	}
	
	private boolean isControl(String className) {
		if ( className == null ) {
			return false;
		}
		if ( className.contains("control-group")) {
			return true;
		}
		if ( className.contains("controls")) {
			return true;
		}
		return false;
	}

}
