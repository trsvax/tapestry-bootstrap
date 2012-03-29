package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Environment;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.components.ButtonGroup;
import com.trsvax.bootstrap.components.DropDown;
import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;
import com.trsvax.bootstrap.environment.FrameworkEnvironment;

public class ButtonGroupProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {ButtonGroup.class, DropDown.class};
	
	private final Logger logger;
	private final Environment environment;
	private final String jQueryAlias;

	
	public ButtonGroupProvider(Logger logger, Environment environment, @Symbol(JQuerySymbolConstants.JQUERY_ALIAS) String alias) {
		this.logger = logger;
		this.environment = environment;
		jQueryAlias = alias;
	}


	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! ButtonGroup.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		ButtonGroupEnvironment buttonGroupEnvironment = environment.peek(ButtonGroupEnvironment.class);
		
		
		logger.info("handle buttongroup");


		final List<Element> links = new ArrayList<Element>();
		final List<Element> dropdowns = new ArrayList<Element>();
		
		
		mixin.getRoot().visit(visitor(links,dropdowns));
		if ( dropdowns.size() > 0 ) {
			for ( int i = 0; i < links.size(); i++ ) {
				if ( i == 0 ) {
					Element element = links.get(i);
					
					String href = element.getAttribute("href");
					if ( href.equals("#")) {						
						element.addClassName("btn dropdown-toggle");
						element.attribute("data-toggle", "dropdown");
						element.element("span", "class","caret");
					} else {
						element.addClassName("btn");
						Element a = element.element("a", "class","btn dropdown-toggle","data-toggle","dropdown");
						a.element("span", "class","caret");
					}
				} else {
					wrapLI(links.get(i));
				}
			}
		} else {
			for ( Element element : links ) {
				element.addClassName("btn");
			}
		}
		return true;
	}
	
	Visitor visitor(final List<Element> links, final List<Element> dropdowns) {

		return new Visitor() {			
			public void visit(Element element) {
				if ( anchor(element) ) {
					links.add(element);
				}
				if ( hasComponent("DropDown", element)) {
					dropdowns.add(element);
				}
				
			}
		};
	}

	public boolean hasComponent(String name, Element element) {
		if ( name == null ) {
			return false;
		}
		String component = element.getAttribute("tb.component");
		if ( component == null ) {
			return false;
		}
		if ( component.toLowerCase().contains(name.toLowerCase()) ) {
			return true;
		}
		return false;
	}


	public boolean instrument(FrameworkMixin mixin) {
		ButtonGroupEnvironment buttonGroupEnvironment = environment.peek(ButtonGroupEnvironment.class);
		if ( buttonGroupEnvironment != null && ! buttonGroupEnvironment.isInstrumented() ) {
			return false;
		}
		String name = mixin.getComponentClassName();

		for ( Class<?> clazz : handles ) {
			if ( clazz.getCanonicalName().equals(name)) {
				return true;
			}
		}

		return false;
	}
	
	void scriptOnce(String script) {
		FrameworkEnvironment excludeEnvironment = environment.peek(FrameworkEnvironment.class);
		if ( excludeEnvironment != null ) {
			excludeEnvironment.addScriptOnce(script);
		}
	}

}
