package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

//@ImportJQueryUI(value = {"jquery.ui.widget", "jquery.ui.mouse", "jquery.ui.draggable"})
@Import(library = { "classpath:com/trsvax/bootstrap/assets/mixins/draggable/draggable.js" })

@MixinAfter
public class Draggable {
	
	@Inject
	JavaScriptSupport javaScriptSupport;
	
	@Parameter
	private String elementName;
	@Parameter
	private String draggableName;
	
	@Inject
	ComponentResources resources;
	
	private Element element;
	
	private JSONObject spec;
	
	@Inject
	private Logger logger;
	
	@SetupRender
	void beginRender() {
		JSONObject params = new JSONObject();
		params.put("appendTo","body");
		params.put("helper","clone");
		
		spec = new JSONObject();
		spec.put("params",params);
		//logger.info("spe1c {}",spec);

		
	}

	@AfterRender
	public void afterRender(MarkupWriter writer) {
		String id = null;
		if ( elementName == null ) {
			elementName = resources.getElementName();
			if ( elementName == null ) {
				elementName = "ul";
			}
		}
		if ( draggableName == null ) {
			draggableName = "div";
			if ( elementName.equals("ul")) {
					draggableName = "li";
			}
		}

		Object compoment =  resources.getContainer();
		if ( ClientElement.class.isAssignableFrom(compoment.getClass()) ) {
			id = ((ClientElement)compoment).getClientId();
		} else {
			id = javaScriptSupport.allocateClientId(resources);
		}
		if ( Grid.class.isAssignableFrom(compoment.getClass()) ) {
			elementName = "tbody";
			draggableName = "tr";
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
		//logger.info("spec {}",spec);
		if ( ! spec.has("selector")) {
			spec.put("selector",String.format("#%s %s",id,draggableName));
		}
		
		javaScriptSupport.addInitializerCall("jqDraggable", spec);
		
	}
	
	public JSONObject getSpec() {
		return spec;
	}
}
