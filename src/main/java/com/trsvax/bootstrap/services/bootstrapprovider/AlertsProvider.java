package com.trsvax.bootstrap.services.bootstrapprovider;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.corelib.components.Alerts;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.environment.AlertsEnvironment;

public class AlertsProvider extends AbstractFrameworkProvider implements BootstrapProvider  {

	public class AlertsVisitor implements Visitor {

		Element controls;
		final String type;
		final Set<Element> pop;
		final FrameworkMixin mixin;
		
		public AlertsVisitor(final FrameworkMixin mixin, final String type, final Set<Element> pop) {
			this.type = type;
			this.pop = pop;
			this.mixin = mixin;
		}

		public void visit(Element element) {
			if (!hasName("div", element)) {
				return;
			}
			
			javaScriptSupport.importStylesheet(alertsCss);
			javaScriptSupport.importJavaScriptLibrary(alertsJs);
			javaScriptSupport.addInitializerCall(InitializationPriority.EARLY, "bootstrapAlerts", new JSONArray());
		}

	}

	private final Class<?>[] handles = {Alerts.class};
	private final Class<AlertsEnvironment> environmentClass = AlertsEnvironment.class;
	private final Environment environment;
	@SuppressWarnings("unused")
	private final Logger logger;
	
	@Inject @Path("classpath:com/trsvax/bootstrap/t5-bootstrap-alerts.js")
	private Asset alertsJs;
	
	@Inject @Path("classpath:com/trsvax/bootstrap/t5-bootstrap-alerts.css")
	private Asset alertsCss;
	private JavaScriptSupport javaScriptSupport;

	public AlertsProvider(Environment environment, Logger logger, JavaScriptSupport javaScriptSupport) {
		this.environment = environment;
		this.logger = logger;
		this.javaScriptSupport = javaScriptSupport;
	}
	
	public boolean instrument(FrameworkMixin mixin) {
		return instrument(mixin, environment.peekRequired(environmentClass), handles);
	}

	boolean handle(FrameworkMixin mixin) {
		if ( Alerts.class.getCanonicalName().equals(mixin.getComponentClassName())) {
			return true;
		}
		
		return false;
	}
	
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {

		if ( ! handle(mixin)) {
			return false;
		}
		
		final AlertsEnvironment alertsEnvironment = environment.peekRequired(environmentClass);
		final String type = alertsEnvironment.getType(mixin);
		if ( type == null ) {
			return false;
		}
		
		final Set<Element> pop = new HashSet<Element>();
		mixin.getRoot().visit( new AlertsVisitor(mixin,type,pop));
		for ( Element element : pop ) {
			element.pop();
		}
		return true;
	}
}
