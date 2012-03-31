package com.trsvax.bootstrap.services.bootstrapprovider;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.components.NavBar;

public class NavBarProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {NavBar.class};
	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! NavBar.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		final String type = mixin.getType();
		mixin.getRoot().visit( new Visitor() {
			
			public void visit(Element element) {
				
				if ( div(element) && hasClass("navbar",element)) {
					element.addClassName(type);
				}
				
			}
		});
		return true;
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
