package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.services.Environment;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.FormEnvironment;

public class FormProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	private final Class<?>[] handles = {BeanEditForm.class};
	private final Class<FormEnvironment> environmentClass = FormEnvironment.class;
	private final Environment environment;
	private final Logger logger;
	
	public FormProvider(Environment environment, Logger logger) {
		this.environment = environment;
		this.logger = logger;
	}

	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		
		if ( ! BeanEditForm.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return false;
		}
		final FormEnvironment formEnvironment = environment.peekRequired(environmentClass);
		final String type = formEnvironment.getType(mixin);
		if ( type == null ) {
			return false;
		}
		if ( ! type.startsWith(formEnvironment.getPrefix())) {
			return false;
		}
		
		final Set<Element> pop = new HashSet<Element>();
		mixin.getRoot().visit( new BeanEditFormVisitor(type,pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}
	
	class BeanEditFormVisitor implements Visitor {
		Element controls;
		final String type;
		final Set<Element> pop;
		
		public BeanEditFormVisitor(final String type, final Set<Element> pop) {
			this.type = type;
			this.pop = pop;
		}

		public void visit(Element element) {
			Element buttonContainer;

			if ( form(element)) {
				element.addClassName(type);
				return;
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
					controls = element.wrap("div", "class", "controls");
				}
			}
			if ( textarea(element) ) {
				element.wrap("div", "class", "controls");
			}
			if ( label(element)) {
				element.addClassName("control-label");
			}
			if ( hasClass("t-calendar-trigger",element)) {
				element.moveToBottom(controls);
			}
		}
	}
	


	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

}
