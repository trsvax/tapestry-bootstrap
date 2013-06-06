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
	private Element submit;
	
	@CleanupRender
	void cleanupRender() {
		String formClass = form.getAttribute("class");
		if ( formClass != null && formClass.contains("form-horizontal")) {
			return;
		}
		divs = new ArrayList<Element>();
		form.visit( new Visitor() {
			
			public void visit(Element element) {
				if ( element.getName().equals("div")) {
					divs.add(element);
				}
				String className = element.getAttribute("class");
				if ( className != null && className.contains("control-label")) {
					element.forceAttributes("class",className.replace("control-label", ""));
				}
				String type = element.getAttribute("type");
				if ( type != null && submit == null && type.equals("submit")) {
					element.elementBefore("br");
					submit = element;
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

}
