package com.trsvax.bootstrap.services.bootstrapprovider;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.ButtonGroupEnvironment;

public class ButtonProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Logger logger;
	private final Environment environment;
	
	public ButtonProvider(Logger logger, Environment environment) {
		this.logger = logger;
		this.environment = environment;
	}

	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		String type = mixin.getType();
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith("btn")) {
			return false;
		}
		mixin.getRoot().visit(visitor(type));
		return true;
	}

	
	Visitor visitor(final String type) {
		return new Visitor() {			
			public void visit(Element element) {
				if ( anchor(element) || span(element) || input(element) || button(element)) {
					element.addClassName(getClassForType("btn",type));
				}				
			}
		};
	}


	public boolean instrument(FrameworkMixin mixin) {
		return mixin.getType() != null && mixin.getType().startsWith("btn");
	}

}
