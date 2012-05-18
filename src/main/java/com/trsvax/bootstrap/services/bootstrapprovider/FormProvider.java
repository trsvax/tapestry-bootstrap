package com.trsvax.bootstrap.services.bootstrapprovider;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
			if (div(element) && hasClass("t-error", element)) {
				element.forceAttributes("class", "alert alert-error");
			}
			if (hasClass("t-beaneditor", element)) {
				pop.add(element);
			}
			if (hasClass("t-beaneditor-row", element)) {
				element.forceAttributes("class", "control-group");
			}
			if ( select(element)) {
				controls = element.wrap("div", "class", "controls");
				markErrors(element);
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
					markErrors(element);
				}
			}
			if ( textarea(element) ) {
				element.wrap("div", "class", "controls");
				markErrors(element);
			}
			if ( label(element)) {
				element.addClassName("control-label");
			}
			if ( hasClass("t-calendar-trigger",element)) {
				element.moveToBottom(controls);
			}
		}

		/**
		 * If the element is marked as error by Tapesty, add the CSS class "error" to the control-group DIV.
		 *
		 * Moves the "help-inline" error message created but the ValidationDecorator as a child of the control DIV.
		 *
		 * @param element The input/select element to check for error
		 */
		private void markErrors(Element element) {
			if (hasClass("error", element)){
				controls.getContainer().addClassName("error");
				Element helpInline = controls.getContainer().getElementByAttributeValue("class", "help-inline");
				if (helpInline != null){
					helpInline.moveAfter(element);
				}
			}
		}
	}
	


	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

}
