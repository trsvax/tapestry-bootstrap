package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
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
	private final Class<TableEnvironment> environmentClass = TableEnvironment.class;
	private final Environment environment;
	private final Logger logger;

	
	public TableProvider(Environment environment, Logger logger) {
		this.logger = logger;
		this.environment = environment;
	}


	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! Grid.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		final TableEnvironment tableEnvironment = environment.peekRequired(environmentClass);
		final String type = tableEnvironment.getType(mixin);
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith(tableEnvironment.getPrefix())) {
			return false;
		}
		final Set<Element> pop = new HashSet<Element>();
		mixin.getRoot().visit(new Visitor() {
			
			public void visit(Element element) {
				if ( div(element) && hasClass("t-data-grid", element) ) {
					pop.add(element);
				}
				if ( table(element) && hasClass("t-data-grid",element)) {
					element.forceAttributes("class", getClassForType(tableEnvironment.getPrefix(), type));
				}
				if ( img(element) && hasClass("t-sort-icon",element)) {
					element.elementBefore(tableEnvironment.getSortElement(), tableEnvironment.getSortElementAttributes());
					element.remove();
				}
				if ( th(element)) {
					String classes = element.getAttribute("class");
					if ( classes != null ) {
						String newClasses = "";
						for ( String c : classes.split(" ")) {
							if ( c.startsWith("t-")) {
								newClasses += " " + c;
							} else {
								newClasses += " fw-" + c;
							}
						}
						element.forceAttributes("class",newClasses);
					}
				}
				
			}
		});
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}


	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

}
