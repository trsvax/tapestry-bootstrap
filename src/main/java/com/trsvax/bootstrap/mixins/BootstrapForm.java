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
	private List<Element> removeElements;
	private boolean broken;
	private String formClass;
	
	@Inject
	private Logger logger;
		
	@BeginRender
	void beginRender(MarkupWriter writer) {
		form = writer.getElement();	
	}
	
	
	@CleanupRender
	void cleanupRender() {
		formClass = form.getAttribute("class");
		if ( isHorizontalForm()) {
			return;
		}
		removeElements = new ArrayList<Element>();
		form.visit( new Visitor() {
			
			public void visit(Element element) {
				String className = element.getAttribute("class");
				if ( isDiv(element) && (isControlClass(className) || isFormActions(className)) ) {
					removeElements.add(element);
				}			
				if ( isControLabel(className)) {
					String labelClass = className.replace("control-label", "").trim();
					element.forceAttributes("class", labelClass.length() == 0 ? null : labelClass);
				}
				if ( isDiv(element) && isButtonGroup(className) && needBR() ) {
						element.elementBefore("br");
				}
				if ( isButtonGroup(className) && removeButtonGroup()) {
					removeElements.add(element);
				}				
			}
		});
		for ( Element element : removeElements ) {
			element.pop();
		}
		if ( needFieldSet()) {
			List<Node> children = form.getChildren();
			Element fieldset = form.element("fieldset");
			for ( Node child : children ) {
				child.moveToBottom(fieldset);
			}
		}
		
	}
	
	private boolean isDiv(Element element) {
		return "div".equals( element.getName());
	}
	
	private boolean isControLabel(String className) {
		if ( className == null ) {
			return false;
		}
		return className.contains("control-label");
	}
	
	private boolean needBR() {
		if ( broken || isInlineForm() || isSearchForm() ) {
			return false;
		}
		broken = true;
		return true;
	}
	
	private boolean removeButtonGroup() {
		if ( isInlineForm() || isSearchForm() ) {
			return true;
		}
		return false;
	}
	
	private boolean needFieldSet() {
		if ( isDefaultForm() ) {
			return true;
		}
		return false;
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
	
	private boolean isControlClass(String className) {
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
	
	private boolean isDefaultForm() {
		if ( isSearchForm() || isInlineForm() || isHorizontalForm() ) {
			return false;
		}
		
		return true;
	}
	
	private boolean isSearchForm() {
		if ( "form-search".equals(formClass)) {
			return true;
		}
		return false;
	}
	
	private boolean isInlineForm() {
		if ( "form-inline".equals(formClass)) {
			return true;
		}
		return false;
	}
	
	private boolean isHorizontalForm() {
		if ( "form-horizontal".equals(formClass)) {
			return true;
		}
		return false;
	}

}
