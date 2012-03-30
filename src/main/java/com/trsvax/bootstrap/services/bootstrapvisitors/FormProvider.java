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
import com.trsvax.bootstrap.environment.FormEnvironment;
import com.trsvax.bootstrap.environment.TableEnvironment;

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
		mixin.getRoot().visit(beanEditForm(type,pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
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
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

}
