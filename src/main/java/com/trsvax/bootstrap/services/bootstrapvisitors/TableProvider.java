package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;

public class TableProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Logger logger;
	private final String prefix = "table";
	
	public TableProvider(Logger logger) {
		this.logger = logger;
	}


	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		final Set<Element> pop = new HashSet<Element>();
		final String type = mixin.getType();
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith(prefix)) {
			return false;
		}
		logger.info("table {}",writer.getElement());
		mixin.getRoot().visit(new Visitor() {
			
			public void visit(Element element) {
				if ( div(element) && hasClass("t-data-grid", element) ) {
					pop.add(element);
				}
				if ( table(element) && hasClass("t-data-grid",element)) {
					element.forceAttributes("class", getClassForType(prefix, type));
				}
				
			}
		});
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}


	public boolean instrument(FrameworkMixin mixin) {
		return mixin.getType() != null && mixin.getType().startsWith(prefix);
	}

}
