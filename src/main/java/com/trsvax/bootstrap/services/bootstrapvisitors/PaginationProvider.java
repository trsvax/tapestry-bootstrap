package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.corelib.components.GridPager;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.GridPagerEnvironment;

public class PaginationProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {GridPager.class};
	private final Class<GridPagerEnvironment> environmentClass = GridPagerEnvironment.class;
	private final Environment environment;
	private final Logger logger;
	
	public PaginationProvider(Environment environment, Logger logger) {
		this.environment = environment;
		this.logger = logger;
	}
	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer ) {
		if ( ! GridPager.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		final GridPagerEnvironment gridPagerEnvironment = environment.peekRequired(environmentClass);
		final String type = gridPagerEnvironment.getType(mixin);
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith(gridPagerEnvironment.getPrefix())) {
			return false;
		}
		List<Element> pop = new ArrayList<Element>();
		mixin.getRoot().visit( new GridVisitor(pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}

	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

	class GridVisitor implements Visitor  {
		private Element ul;
		private List<Element> pop;
		
		public GridVisitor(List<Element> pop) {
			this.pop = pop;
		}

		public void visit(Element element) {
			if ( hasClass("t-data-grid-pager", element)) {
				pop.add(element);
				ul = element.elementBefore("ul");
				ul.addClassName("pagination");
			}
			if ( anchor(element) ) {
				if ( ul != null ) {
				Element li = element.wrap("li");
				
					li.moveToBottom(ul);
				}
			}
			if ( span(element)) {
				if ( ul != null ) {
				Element a = element.wrap("a","href","#");
				Element li = a.wrap("li");
				
					li.moveToBottom(ul);
				}
			}
			
			
		}
		
		
	}
	
}
