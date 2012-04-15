package com.trsvax.bootstrap.services.bootstrapprovider;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.components.Breadcrumb;
import com.trsvax.bootstrap.environment.BreadcrumbEnvironment;

public class BreadcrumbProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {Breadcrumb.class};
	private final Environment environment;
	private final Class<BreadcrumbEnvironment> environmentClass = BreadcrumbEnvironment.class;

	public BreadcrumbProvider(Environment environment) {
		this.environment = environment;
	}
	
	public boolean setupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( handle(mixin) ) {
			return true;
		}
		return false;
	}
	
	


	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! handle(mixin)) {
			return false;
		}
		
		List<Element> elements = new ArrayList<Element>();
		mixin.getRoot().visit(new BreadcrumbVisitor(elements));
		if ( elements.size() > 0 ) {
			for ( int i = 0; i < elements.size()-1; i++ ) {
				Element element = elements.get(i);
				wrapLI(element).element("span", "class","divider");
				
			}
			Element element = elements.get(elements.size()-1);
			wrapLI(element).addClassName("active");
			element.pop();
		}
		
		return true;
	}

	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}
	
	private boolean handle(FrameworkMixin mixin) {
		for ( Class<?> handle : handles ) {
			if ( handle.getCanonicalName().equals(mixin.getComponentClassName())) {
				return true;
			}
		}
		
		return false;
	}
	
	class BreadcrumbVisitor implements Visitor {
		private final List<Element> elements;
		
		public BreadcrumbVisitor(List<Element> elements) {
			this.elements = elements;
		}
		

		public void visit(Element element) {
			if ( anchor(element)) {
				elements.add(element);
			}
			
		}
		
	}

}
