package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.components.ButtonGroup;
import com.trsvax.bootstrap.components.DropDown;
import com.trsvax.bootstrap.components.Nav;
import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;
import com.trsvax.bootstrap.environment.ButtonGroupValues;
import com.trsvax.bootstrap.environment.NavEnvironment;
import com.trsvax.bootstrap.environment.NavValues;

public class NavProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {Nav.class};
	private final Environment environment;
	private final Logger logger;
	
	public NavProvider(Environment environment, Logger logger) {
		this.logger = logger;
		this.environment = environment;
	}
	
	public boolean setupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! Nav.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		NavEnvironment navEnvironment = environment.peek(NavEnvironment.class);
		environment.push(NavEnvironment.class, new NavValues(navEnvironment).withInNav(true));
		return true;
	}
	
	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! environment.peekRequired(NavEnvironment.class).isInNav() ) {
			return false;
		}
		if ( ButtonGroup.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			handleButtonGroup(mixin,writer);
			return true;
		}
		if ( DropDown.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			handleDropDown(mixin,writer);
			return true;
		}
		if ( ! Nav.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		final String type = mixin.getType();
		final List<Element> anchors = new ArrayList<Element>();

		//TODO add support for Split button dropdowns
		mixin.getRoot().visit(new Visitor() {
			
			public void visit(Element element) {
				if ( anchor(element) ) {
					anchors.add(element);
				}	
				if ( ul(element) && hasClass("nav", element)) {
					element.addClassName(type);
				}
				if ( div(element) && hasClass("btn-group",element)) {
					element.wrap("li", "class","dropdown");
					element.pop();
				}
			}
		});
		Element active = null;
		for ( Element element : anchors ) {
			Element li = element.getContainer();
			if ( ! hasName("li", li)) {
				li = element.wrap("li");
			} 
			if ( hasClass("active",element) || active == null) {
				active = li;
			}			
		}
		if ( active != null ) {
			active.addClassName("active");
		}
		environment.pop(NavEnvironment.class);
		return true;
	}




	private void handleDropDown(FrameworkMixin mixin, MarkupWriter writer) {
		final List<Element> anchors = new ArrayList<Element>();
		mixin.getRoot().visit(new Visitor() {
			
			public void visit(Element element) {
				if ( anchor(element) ) {
					anchors.add(element);
				}
				
			}
		});
		for ( Element element : anchors ) {
			wrapLI(element);
		}
	}


	private void handleButtonGroup(FrameworkMixin mixin, MarkupWriter writer) {
		final List<Element> anchors = new ArrayList<Element>();

		mixin.getRoot().visit(new Visitor() {			
			public void visit(Element element) {
				if ( anchor(element) ) {
					anchors.add(element);
				}				
			}
		});	
		if ( anchors.size() > 0 ) {
			Element anchor = anchors.get(0);
			anchor.addClassName("dropdown-toggle");
			anchor.attribute("data-toggle", "dropdown");
			anchor.element("b", "class","caret");
			
		}
	}

	public boolean instrument(FrameworkMixin mixin) {
		String name = mixin.getComponentClassName();

		for ( Class<?> clazz : handles ) {
			if ( clazz.getCanonicalName().equals(name)) {
				return true;
			}
		}

		return false;
	}

}
