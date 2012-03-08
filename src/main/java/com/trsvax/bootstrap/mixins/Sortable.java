package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
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
	
	@Parameter(defaultPrefix="literal")
	private String event;
	
	@Parameter(defaultPrefix="literal")
	private String connectWith;
	
	@Parameter(defaultPrefix="literal")
	private String containment;
	
	private Element element;
	
	@Inject
	JavaScriptSupport javaScriptSupport;
	
	@Inject
	ComponentResources resources;
	
	@Inject
	Logger logger;
	
	private JSONObject spec;
			
		
	@BeginRender
	void beginRender() {
		if ( event == null ) {
			event = "sort";
		}

		String link = resources.createEventLink(event).toAbsoluteURI();
		if ( context != null ) {
				link = resources.createEventLink(event,context).toAbsoluteURI();
		}
		
		JSONObject params = new JSONObject();
		if ( containment != null ) {
			params.put("containment", containment);
		}
		if ( connectWith != null ) {
			params.put("connectWith",connectWith);
		}
		spec = new JSONObject();
		spec.put("params", params);
		spec.put("BaseURL",link);		
	}
	
	@AfterRender
	public void afterRender(MarkupWriter writer) {
		String id = null;
		Object compoment =  resources.getContainer();
		if (  ClientElement.class.isAssignableFrom(compoment.getClass()) ) {
			id = ((ClientElement)compoment).getClientId();
		} else {
			id = javaScriptSupport.allocateClientId(resources);
		}
		if ( elementName == null ) {
			elementName = resources.getElementName();
		}
		if ( Grid.class.isAssignableFrom(compoment.getClass()) ) {
			elementName = "tbody";
		}
		
		//element = writer.getElement();
		
		if ( elementName != null ) {
			writer.getElement().visit( new Visitor() {
				boolean first = true;
				public void visit(Element e) {
					if ( e.getName().equals(elementName)) {
						if ( first ) {
							first = false;
							element = e;
						}
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
		if ( ! spec.has("selector")) {
			spec.put("selector", "#"+id);
		}

		javaScriptSupport.addInitializerCall("jqSortable", spec);
	}
	
	public JSONObject getSpec() {
		return spec;
	}
	
 
}
