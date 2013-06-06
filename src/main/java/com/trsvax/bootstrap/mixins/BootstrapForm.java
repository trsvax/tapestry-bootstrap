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

@MixinAfter
public class BootstrapForm {
	
	private Element form;
	private List<Element> removeElements;
	private boolean broken;
	private String formClass;
			
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
				if ( isDiv(element) && isButtonGroup(className) && addBR() ) {
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
		if ( addFieldSet()) {
			List<Node> children = form.getChildren();
			Element fieldset = form.element("fieldset");
			for ( Node child : children ) {
				child.moveToBottom(fieldset);
			}
		}
		
	}
	
	private boolean addBR() {
		return broken || isInlineForm() || isSearchForm() ? false : (broken = true);
	}
	
	private boolean addFieldSet() {
		return isDefaultForm();
	}
	
	private boolean removeButtonGroup() {
		return isInlineForm() || isSearchForm();
	}
		
	private boolean isControLabel(String className) {
		return className == null ? false : className.contains("control-label");
	}
	
	private boolean isButtonGroup(String className) {
		return className == null ? false : className.contains("btn-group");
	}
	
	private boolean isFormActions(String className) {
		return className == null ? false : className.contains("form-actions");
	}
	
	private boolean isControlClass(String className) {
		return className == null ? false : className.contains("controls") || className.contains("control-group") ;
	}
	
	private boolean isDiv(Element element) {
		return "div".equals(element.getName());
	}
	
	private boolean isSearchForm() {
		return "form-search".equals(formClass);
	}
	
	private boolean isInlineForm() {
		return "form-inline".equals(formClass);
	}
	
	private boolean isHorizontalForm() {
		return "form-horizontal".equals(formClass);
	}
	
	private boolean isDefaultForm() {
		return isSearchForm() || isInlineForm() || isHorizontalForm() ? false : true;
	}

}
