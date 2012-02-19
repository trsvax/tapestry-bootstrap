package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.ImportJQueryUI;

@ImportJQueryUI(value = {"jquery.ui.widget", "jquery.ui.mouse", "jquery.ui.droppable"})
@MixinAfter
public class Droppable {	
	@Parameter
	Object context;
	@Parameter
	String event;
	
	@Inject
	JavaScriptSupport javaScriptSupport;
	
	@Parameter
	private String elementName;
	
	@Inject
	ComponentResources resources;
	
	private Element element;
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		String id = null;
		if ( elementName == null ) {
			elementName = "ul";
		}
		if ( event == null ) {
			event = "drop";
		}
		Object compoment =  resources.getContainer();
		if ( ClientElement.class.isAssignableFrom(compoment.getClass()) ) {
			id = ((ClientElement)compoment).getClientId();
		} else {
			id = javaScriptSupport.allocateClientId(resources);
		}
		if ( Grid.class.isAssignableFrom(compoment.getClass()) ) {
			elementName = "tbody";
		}
		
		element = writer.getElement();
		
		if ( elementName != null ) {
			element.visit( new Visitor() {
				
				public void visit(Element e) {
					if ( e.getName().equals(elementName)) {
						element = e;
					}
					if ( e.getName().equals("tr"))  {
						String c = e.getAttribute("class");
						if ( c != null ) {
							e.forceAttributes("id",c.split(" ")[0]);
						}
					}
					
				}
			});
		}
		if ( element != null ) {
			String currentID = element.getAttribute("id");
			if ( currentID != null ) {
				id = currentID;
			} else {
				element.forceAttributes("id",id);
			}	
			//element.addClassName("sortable");
		}
		String link = resources.getContainerResources().createEventLink(event).toAbsoluteURI();
		if ( context != null ) {
				resources.getContainerResources().createEventLink(event,context).toAbsoluteURI();
		}
		
		javaScriptSupport.addScript("$(function() { " +
				"$('#%s').droppable({" +
					"activeClass: 'ui-state-default'," +
					"hoverClass: 'ui-state-hover'," + 
					"accept: ':not(.ui-sortable-helper)'," +
					"drop: function(event,ui) {" +
					"$('<li></li>').text(ui.draggable.text()).appendTo(this);" +
					"$.get('%s',{drop:ui.draggable.attr('id')});" +
					"}" +
				"});" +
		"});",id,link);
		
	}

}
