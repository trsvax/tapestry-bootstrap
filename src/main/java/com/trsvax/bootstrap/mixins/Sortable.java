package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.ImportJQueryUI;
import org.slf4j.Logger;

@ImportJQueryUI(value = {"jquery.ui.widget", "jquery.ui.mouse", "jquery.ui.sortable"})
@Import(library = { "classpath:com/trsvax/bootstrap/assets/mixins/sortable/sortable.js" })

@MixinAfter
public class Sortable {
		
	@Parameter
	Object context;
	
	@Parameter
	private String elementName;
	
	@Parameter
	private String event;
	
	private Element element;
	
	@Inject
	JavaScriptSupport javaScriptSupport;
	
	@Inject
	ComponentResources resources;
	
	@Inject
	Logger logger;
			
		
	@AfterRender
	void afterRender(MarkupWriter writer) {
		String id = null;
		if ( event == null ) {
			event = "sort";
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
			element.addClassName("sortable");
		}
		String link = resources.createEventLink(event).toAbsoluteURI();
		if ( context != null ) {
				link = resources.createEventLink(event,context).toAbsoluteURI();
		}
		
		JSONObject params = new JSONObject();
		JSONObject spec = new JSONObject();
		spec.put("parmas", params);
		spec.put("selector", "#"+id);
		spec.put("BaseURL",link);
		javaScriptSupport.addInitializerCall("jqSortable", spec);
				
		/*
		javaScriptSupport.addScript("$(function() { " +
				"$('#%s').sortable({" +
					" update: function(event, ui) { " +
					"    var order = $(this).sortable('toArray').toString(); " +
					"    $.get('%s',{order:order});" +
					" }" + 
				"});" +
		"});",id,link );
		*/
		
	}
	
 
}
