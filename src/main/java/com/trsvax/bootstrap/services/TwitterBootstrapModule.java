package com.trsvax.bootstrap.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.ExcludeEnvironment;
import com.trsvax.bootstrap.environment.ExcludeValues;


/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class TwitterBootstrapModule
{
	
    public static void bind(ServiceBinder binder) {
    	binder.bind(BindingFactory.class,SessionBindingFactory.class).withId("SessionBindingFactory");
    	binder.bind(BindingFactory.class,EnvironmentBindingFactory.class).withId("EnvironmentBindingFactory");
    }
    
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("tb", "com.trsvax.bootstrap"));
    }
    
    public static void contributeBindingSource(MappedConfiguration<String, BindingFactory> configuration,
    		@InjectService("SessionBindingFactory") BindingFactory sessionBindingFactory,
    		@InjectService("EnvironmentBindingFactory") BindingFactory environmentBindingFactory
    		) {
        configuration.add("session", sessionBindingFactory);  
        configuration.add("env", environmentBindingFactory);
    }
    
    @Contribute(ComponentClassTransformWorker2.class)   
    public static void  provideWorkers(OrderedConfiguration<ComponentClassTransformWorker2> workers) {    
        workers.addInstance("ConnectWorker", ConnectWorker.class);
        workers.addInstance("ExcludeWorker", ExcludeWorker.class);
    } 
   
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration,
    		final Environment environment, @Symbol(SymbolConstants.EXECUTION_MODE) final String mode) {
    	MarkupRendererFilter filter = new MarkupRendererFilter() {
			
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				final ExcludeValues values = new ExcludeValues();
				environment.push(ExcludeEnvironment.class, values);
				renderer.renderMarkup(writer);				
				environment.pop(ExcludeEnvironment.class);
				
				Element head = writer.getDocument().getRootElement().find("head");
				head.visit( new Visitor() {
					
						public void visit(Element element) {
							String type = element.getAttribute("type");
							String href = element.getAttribute("href");
							if ( type != null && href != null && type.equals("text/css")) {
								for ( String pattern : values.getExcludes(null)) {
									if ( href.contains(pattern)) {
										element.remove();
									}
								}
								for ( String pattern : values.getExcludes(mode)) {
									if ( href.contains(pattern)) {
										element.remove();
									}
								}
							}
							
						}
					});
				}
			
		};
		configuration.add("ExcludeCSS", filter,"before:*");
    }
   
}
