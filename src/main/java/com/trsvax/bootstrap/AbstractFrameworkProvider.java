package com.trsvax.bootstrap;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;

import com.trsvax.bootstrap.environment.FWEnvironment;
import com.trsvax.bootstrap.environment.TableEnvironment;

public abstract class AbstractFrameworkProvider implements FrameworkProvider {
	
	public boolean setupRender(FrameworkMixin mixin, MarkupWriter writer) {
		return false;
	}
	
	public boolean beginRender(FrameworkMixin mixin, MarkupWriter writer) {
		return false;
	}
	
	public boolean afterRender(FrameworkMixin mixin, MarkupWriter writer) {
		return false;
	}
	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		return false;
	}
	
	public boolean renderMarkup(MarkupWriter writer) {
		return false;
	}
 
	
	public boolean span(Element element) {
		return hasName("span", element);
	}
	public boolean anchor(Element element) {
		return hasName("a", element);
	}
	public boolean input(Element element) {
		return hasName("input", element);
	}
	public boolean label(Element element) {
		return hasName("label", element);
	}
	public boolean img(Element element) {
		return hasName("img", element);
	}
	public boolean table(Element element) {
		return hasName("table", element);
	}
	public boolean div(Element element) {
		return hasName("div", element);
	}
	public boolean form(Element element) {
		return hasName("form", element);
	}
	public boolean th(Element element) {
		return hasName("th", element);
	}
	public boolean td(Element element) {
		return hasName("td", element);
	}
	public boolean button(Element element) {
		return hasName("button", element);
	}
	public boolean ul(Element element) {
		return hasName("ul", element);
	}
	public boolean textarea(Element element) {
		return hasName("textarea", element);
	}

	public boolean hasName(String name, Element element) {
		if ( isPopped(element) ) {
			return false;
		}
		if (element.getName().toLowerCase().equals(name.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	public boolean hasComponent(String name, Element element) {
		if ( name == null ) {
			return false;
		}
		String component = element.getAttribute("tb.component");
		if ( component == null ) {
			return false;
		}
		if ( component.toLowerCase().contains(name) ) {
			return true;
		}
		return false;
	}
	
	public boolean hasClass(Element element) {
		return element.getAttribute("class") != null;
	}

	public boolean hasClass(String className, Element element) {
		if ( isPopped(element) ) {
			return false;
		}
		String c = element.getAttribute("class");
		if (c == null || className == null || c.length() == 0
				|| className.length() == 0) {
			return false;
		}
		String[] classes = c.split(" ");
		for (String s : classes) {
			if (className.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	protected String getClassForType(String prefix, String type) {
		if ( type.startsWith(prefix + "-")) {
			boolean hasPrefix = false;
			String[] types = type.split(" ");
			for ( String t : types ) {
				if ( t.equals(prefix)) {
					hasPrefix = true;
				}
			}
			if ( ! hasPrefix ) {
				type = prefix + " " + type;
			}

		}
		return type;
	}
	
	public void pop(Element element) {
		element.attribute("popped", "true");
	}
	
	boolean isPopped(Element element) {
		String pop = element.getAttribute("popped");
		if ( pop == null ) {
			return false;
		}
		if ( pop.equals("true")) {
			return true;
		}
		return false;
	}
	
	public boolean isActive(Element element) {	
		String active = element.getAttribute("active");
		if (active != null && active.equals("true")) {
			return true;
		}
		return hasClass("active", element);
	}

	public Element wrapLI(Element element) {
		Element li = element.wrap("li");
		if ( isActive(element)) {
			li.addClassName("class", "active");
		}
		return li;
	}
	
	public Element wrap(Element root, String elementName) {
		Element element = root.wrap(elementName);
		if ( hasClass(root)) {
			element.addClassName(root.getAttribute("class"));
		}
		String id = root.getAttribute("id");
		if ( id != null ) {
			element.forceAttributes("id",id);
		}
		return element;
	}
	
	public boolean instrument(FrameworkMixin mixin, FWEnvironment environment, Class<?>[] handles) {
		String name = mixin.getComponentClassName();
		String type = environment.getType(mixin);
		for ( Class<?> clazz : handles ) {
			if ( clazz.getCanonicalName().equals(name)) {
				return type != null && type.startsWith(environment.getPrefix());
			}
		}
		return false;		
	}
	

}
