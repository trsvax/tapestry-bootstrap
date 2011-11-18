package com.trsvax.bootstrap.services;

import java.util.Map.Entry;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.beaneditor.DataTypeConstants;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.internal.services.ValidationDecoratorFactoryImpl;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockOverrideSource;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.ValidationDecoratorFactory;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

import com.trsvax.bootstrap.environment.ExcludeEnvironment;
import com.trsvax.bootstrap.environment.ExcludeValues;


/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class BootstrapModule {
	
    public static void bind(ServiceBinder binder) {
    	binder.bind(BindingFactory.class,SessionBindingFactory.class).withId("SessionBindingFactory");
    	binder.bind(BindingFactory.class,EnvironmentBindingFactory.class).withId("EnvironmentBindingFactory");
    	binder.bind(StringTemplateParser.class,StringTemplateParserImpl.class);
    	binder.bind(ValidationDecoratorFactory.class,BootStrapValidationDecoratorFactoryImpl.class).withId("BootStrapValidation");
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
    		final Environment environment, @Symbol(SymbolConstants.EXECUTION_MODE) final String mode,
    		final JavaScriptSupport javaScriptSupport) {
    	
    	MarkupRendererFilter excludeFilter = new MarkupRendererFilter() {		
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				environment.push(ExcludeEnvironment.class, new ExcludeValues());
				renderer.renderMarkup(writer);				
				final ExcludeEnvironment values = environment.pop(ExcludeEnvironment.class);
				
				Element head = writer.getDocument().getRootElement().find("head");
				head.visit( new Visitor() {					
						public void visit(Element element) {
							String type = element.getAttribute("type");
							String href = element.getAttribute("href");
							if ( type != null && href != null && type.equals("text/css")) {
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
		
		MarkupRendererFilter javaScriptFilter = new MarkupRendererFilter() {		
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				renderer.renderMarkup(writer);
				ExcludeEnvironment values = environment.peek(ExcludeEnvironment.class);
				for ( Entry<String, String> script : values.getOnceScripts()) {
					javaScriptSupport.addScript(script.getKey());
				}
			}
		};
		
		
		configuration.add("JavaScriptFilter", javaScriptFilter,"after:JavaScriptSupport");
		configuration.add("ExcludeCSS", excludeFilter,"before:*");
    }
    
    public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
    {
        configuration.add("tap-bootstrap", "com/trsvax/bootstrap");
    }
    
    @Contribute(BeanBlockOverrideSource.class)
    public static void provideBootStrapBeanBlocks(Configuration<BeanBlockContribution> configuration) {
    	configuration.add(new EditBlockContribution(DataTypeConstants.TEXT, "BootStrapEditBlocks", DataTypeConstants.TEXT));
    	configuration.add(new EditBlockContribution(DataTypeConstants.NUMBER, "BootStrapEditBlocks", DataTypeConstants.NUMBER));
    	configuration.add(new EditBlockContribution(DataTypeConstants.ENUM, "BootStrapEditBlocks", DataTypeConstants.ENUM));
    	configuration.add(new EditBlockContribution(DataTypeConstants.BOOLEAN, "BootStrapEditBlocks", DataTypeConstants.BOOLEAN));
    	configuration.add(new EditBlockContribution(DataTypeConstants.DATE, "BootStrapEditBlocks", DataTypeConstants.DATE));
    	configuration.add(new EditBlockContribution(DataTypeConstants.PASSWORD, "BootStrapEditBlocks", DataTypeConstants.PASSWORD));
    	configuration.add(new EditBlockContribution(DataTypeConstants.CALENDAR, "BootStrapEditBlocks", DataTypeConstants.CALENDAR));
    	configuration.add(new EditBlockContribution(DataTypeConstants.LONG_TEXT, "BootStrapEditBlocks", DataTypeConstants.LONG_TEXT));
    }
    
    @Contribute(ServiceOverride.class)
    public static void setupApplicationServiceOverrides(MappedConfiguration<Class,Object> configuration, @Local ValidationDecoratorFactory override )
    {
    	configuration.add(ValidationDecoratorFactory.class, override);
    }
   
}
