package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.TableEnvironment;

public class TableProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {Grid.class};
	private final Logger logger;
	private final String prefix = "table";
	private final Environment environment;
	
	public TableProvider(Environment environment, Logger logger) {
		this.logger = logger;
		this.environment = environment;
	}


	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! Grid.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		TableEnvironment tableEnvironment = environment.peek(TableEnvironment.class);
		String t = mixin.getType();
		if ( tableEnvironment != null ) {
			t = tableEnvironment.getType(mixin);
		}
		final Set<Element> pop = new HashSet<Element>();
		final String type = t;
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
		String name = mixin.getComponentClassName();
		String type = mixin.getType();
		TableEnvironment tableEnvironment = environment.peek(TableEnvironment.class);
		if ( tableEnvironment != null ) {
			type = tableEnvironment.getType(mixin);
		}
		for ( Class<?> clazz : handles ) {
			if ( clazz.getCanonicalName().equals(name)) {
				return type != null && type.startsWith(prefix);
			}
		}

		return false;

		
	}

}
