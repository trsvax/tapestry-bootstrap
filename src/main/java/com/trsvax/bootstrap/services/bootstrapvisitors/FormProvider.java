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

public class FormProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Logger logger;
	
	public FormProvider(Logger logger) {
		this.logger = logger;
	}

	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		final Set<Element> pop = new HashSet<Element>();
		final String type = mixin.getType();
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith("form")) {
			return false;
		}
		logger.info("form  {}",mixin.getRoot());
		mixin.getRoot().visit(beanEditForm(type,pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}

	public boolean renderMarkup(MarkupWriter writer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	Visitor beanEditForm(final String type, final Set<Element> pop) {	

		return new Visitor() {

			public void visit(Element element) {
				Element buttonContainer;

				if ( form(element)) {
					element.addClassName(type);
				}
				
				if (hasClass("t-beaneditor", element)) {
					pop.add(element);
				}
				if (hasClass("t-beaneditor-row", element)) {
					element.forceAttributes("class", "control-group");
				}
				if ( input(element)) {
					
					String type= element.getAttribute("type");
					String value = element.getAttribute("value") == null ? "" : element.getAttribute("value") ;
					if ( type != null && type.equals("submit") && ! value.equals("Cancel") ) {
						buttonContainer = element.getContainer();
						buttonContainer.forceAttributes("class","form-actions");
						element.addClassName("btn btn-primary");
					} else if ( value.equals("Cancel")) {
						element.addClassName("btn");
					} else {
						element.wrap("div", "class", "control");
					}
				}
				if ( label(element)) {
					element.addClassName("control-label");
				}
				if ( img(element)) {
					element.remove();
				}
			}
		};
	}

	public boolean instrument(FrameworkMixin mixin) {
		return mixin.getType() != null && mixin.getType().startsWith("form");
	}

}
