package com.trsvax.bootstrap.services.bootstrapprovider;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.corelib.components.BeanDisplay;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.BeanDisplayEnvironment;

public class BeanDisplayProvider extends AbstractFrameworkProvider implements BootstrapProvider {

	public class BeanDisplayVisitor implements Visitor {

		Element controls;
		final String type;
		final Set<Element> pop;
		final FrameworkMixin mixin;
		
		public BeanDisplayVisitor(final FrameworkMixin mixin, final String type, final Set<Element> pop) {
			this.type = type;
			this.pop = pop;
			this.mixin = mixin;
		}

		public void visit(Element element) {
			if (!hasName("dl", element) && !hasClass("t-beandisplay", element)) {
				return;
			}
			
			element.forceAttributes("class", type);
		}

	}

	private final Class<?>[] handles = {BeanDisplay.class};
	private final Class<BeanDisplayEnvironment> environmentClass = BeanDisplayEnvironment.class;
	private final Environment environment;
	@SuppressWarnings("unused")
	private final Logger logger;

	public BeanDisplayProvider(Environment environment, Logger logger) {
		this.environment = environment;
		this.logger = logger;
	}
	
	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

	boolean handle(FrameworkMixin mixin) {
		if ( BeanDisplay.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return true;
		}
		
		return false;
	}

	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {

		if ( ! handle(mixin)) {
			return false;
		}
		
		final BeanDisplayEnvironment beanDisplayEnvironment = environment.peekRequired(environmentClass);
		final String type = beanDisplayEnvironment.getType(mixin);
		if ( type == null ) {
			return false;
		}
		
		final Set<Element> pop = new HashSet<Element>();
		mixin.getRoot().visit( new BeanDisplayVisitor(mixin,type,pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}
}
