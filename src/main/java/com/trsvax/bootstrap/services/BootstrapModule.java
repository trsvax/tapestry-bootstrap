package com.trsvax.bootstrap.services;

import java.util.List;
import java.util.Map.Entry;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Marker;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.ioc.services.ChainBuilder;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockSource;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.PartialMarkupRenderer;
import org.apache.tapestry5.services.PartialMarkupRendererFilter;
import org.apache.tapestry5.services.ValidationDecoratorFactory;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.slf4j.Logger;

import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkProvider;
import com.trsvax.bootstrap.FrameworkVisitor;
import com.trsvax.bootstrap.environment.ButtonEnvironment;
import com.trsvax.bootstrap.environment.ButtonValues;
import com.trsvax.bootstrap.environment.FormEnvironment;
import com.trsvax.bootstrap.environment.FormValues;
import com.trsvax.bootstrap.environment.FrameworkEnvironment;
import com.trsvax.bootstrap.environment.FrameworkValues;
import com.trsvax.bootstrap.environment.NavEnvironment;
import com.trsvax.bootstrap.environment.NavValues;
import com.trsvax.bootstrap.environment.TableEnvironment;
import com.trsvax.bootstrap.environment.TableValues;
import com.trsvax.bootstrap.services.bootstrapvisitors.BootstrapFrameworkVisitor;
import com.trsvax.bootstrap.services.bootstrapvisitors.BootstrapVisitor;
import com.trsvax.bootstrap.services.bootstrapvisitors.ButtonGroupProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.ButtonProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.DefaultProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.FormProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.LayoutProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.NavBarProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.NavProvider;
import com.trsvax.bootstrap.services.bootstrapvisitors.TableProvider;


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
		binder.bind(FrameworkVisitor.class, BootstrapVisitor.class).withId(BootstrapVisitor.id);
		binder.bind(FrameworkVisitor.class,BootstrapFrameworkVisitor.class).withId(BootstrapFrameworkVisitor.id);
		binder.bind(ExcludeVisitor.class,ExcludeVisitorImpl.class);
		binder.bind(EnvironmentSetup.class, EnvironmentSetupImpl.class);
		
		
		binder.bind(BootstrapProvider.class,ButtonProvider.class).withId("BootstrapButton");
		binder.bind(BootstrapProvider.class,ButtonGroupProvider.class).withId("BootstrapButtonGroup");
		binder.bind(BootstrapProvider.class,DefaultProvider.class).withId("BootstrapDefault");
		binder.bind(BootstrapProvider.class,FormProvider.class).withId("BootstrapForm");
		binder.bind(BootstrapProvider.class,LayoutProvider.class).withId("BootstrapLayout");	
		binder.bind(BootstrapProvider.class,NavProvider.class).withId("BootstrapNav");
		binder.bind(BootstrapProvider.class,NavBarProvider.class).withId("BootstrapNavBar");
		binder.bind(BootstrapProvider.class,TableProvider.class).withId("BootstrapTable");	
		
		
		
		binder.bind(FrameworkProvider.class,FrameworkProviderImpl.class).withId("FrameworkProvider");

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
	
	public static void contributeBootstrapProvider(OrderedConfiguration<BootstrapProvider> configuration,
			@InjectService("BootstrapButton") BootstrapProvider buttonProvider,
			@InjectService("BootstrapButtonGroup") BootstrapProvider buttonGroupProvider,
			@InjectService("BootstrapDefault") BootstrapProvider defaultProvider,
			@InjectService("BootstrapForm") BootstrapProvider formProvider,
			@InjectService("BootstrapLayout") BootstrapProvider layoutProvider,
			@InjectService("BootstrapNav") BootstrapProvider navProvider,
			@InjectService("BootstrapNavBar") BootstrapProvider navBarProvider,
			@InjectService("BootstrapTable") BootstrapProvider tableProvider) {
		configuration.add("Button", buttonProvider);
		configuration.add("ButtonGroup", buttonGroupProvider);
		configuration.add("Default",defaultProvider);
		configuration.add("Form", formProvider);
		configuration.add("Layout", layoutProvider);
		configuration.add("Nav", navProvider,"before:ButtonGroup");
		configuration.add("NavBar", navBarProvider);
		configuration.add("Table", tableProvider);
		
	}
	
	@Marker(Primary.class)
	public BootstrapProvider build(List<BootstrapProvider> configuration, ChainBuilder chainBuilder) {
		return chainBuilder.build(BootstrapProvider.class, configuration);
	}
	
	/*
	@Contribute(FrameworkProvider.class)
	public static void provideFrameworks(MappedConfiguration<String, FrameworkProvider> configuration,
			@Primary BootstrapProvider bootstrapProvider) {
		//configuration.add("Bootstrap", bootstrapProvider);
		
	}
	*/

	@Contribute(ComponentClassTransformWorker2.class)   
	public static void  provideWorkers(OrderedConfiguration<ComponentClassTransformWorker2> workers) {    
		workers.addInstance("ConnectWorker", ConnectWorker.class);
		workers.addInstance("ExcludeWorker", ExcludeWorker.class);
		workers.addInstance("FrameworkMixinWorker", FrameworkMixinWorker.class);
	} 
	
	@Contribute(EnvironmentSetup.class)
	public static void provideEnvironmentSetup(MappedConfiguration<Class, Object> configuration) {
		configuration.add(FrameworkEnvironment.class, new FrameworkValues(null).withName("tb"));
		configuration.add(ButtonEnvironment.class, new ButtonValues(null));
		configuration.add(FormEnvironment.class, new FormValues(null));
		configuration.add(NavEnvironment.class, new NavValues(null));
		configuration.add(TableEnvironment.class, new TableValues(null));
	}

	public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration,
			final Logger logger,
			final EnvironmentSetup environmentSetup,
			final Environment environment, 
			final JavaScriptSupport javaScriptSupport, 
			final ExcludeVisitor excludeVistior,
			@InjectService(BootstrapVisitor.id)  final FrameworkVisitor frameworkVisitor,
			@InjectService("FrameworkProvider") final FrameworkProvider frameworkProvider) {

		MarkupRendererFilter bootstrapFilter = new MarkupRendererFilter() {		
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				environmentSetup.push();
				renderer.renderMarkup(writer);				
				final FrameworkEnvironment values = environment.peek(FrameworkEnvironment.class);
				environmentSetup.pop();

				frameworkProvider.renderMarkup(writer);
				
				/*
				Element root = writer.getDocument().getRootElement();
				if ( root != null ) {
					Element head = root.find("head");
					if ( head != null ) {
						head.visit(excludeVistior.visit(values));
					}
					Element body = root.find("body");
					if ( body != null) {				
						frameworkVisitor.visit(body);
					}	
				}
				*/
				
			}		
		};

		MarkupRendererFilter javaScriptFilter = new MarkupRendererFilter() {		
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				renderer.renderMarkup(writer);
				FrameworkEnvironment values = environment.peek(FrameworkEnvironment.class);
				for ( Entry<String, String> script : values.getOnceScripts()) {
					javaScriptSupport.addScript(script.getKey());
				}
			}
		};


		configuration.add("JavaScriptFilter", javaScriptFilter,"after:JavaScriptSupport");
		configuration.add("BootstrapFilter", bootstrapFilter,"before:*");
	}

	public void contributePartialMarkupRenderer(OrderedConfiguration<PartialMarkupRendererFilter> configuration,
			final EnvironmentSetup environmentSetup,
			final Environment environment,
			final JavaScriptSupport javaScriptSupport, 
			final ExcludeVisitor excludeVistior,
			@InjectService(BootstrapVisitor.id) final FrameworkVisitor frameworkVisitor) {
		PartialMarkupRendererFilter bootstrapFilter = new PartialMarkupRendererFilter() {

			public void renderMarkup(MarkupWriter writer, JSONObject reply, PartialMarkupRenderer renderer) {
				environmentSetup.push();
				renderer.renderMarkup(writer,reply);				
				final FrameworkEnvironment values = environment.peek(FrameworkEnvironment.class);
				environmentSetup.pop();

				Element root = writer.getDocument().getRootElement();
				if ( root != null ) {
					Element body = root.find("ajax-partial");
					if ( body != null) {
						frameworkVisitor.visit(body);
					}	
					//This does not seem right
					reply.put("content", body.getChildMarkup());
				}
			}					
		};
		configuration.add("BootstrapAJAXFilter", bootstrapFilter,"before:*");
	}

	public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
	{
		configuration.add("tap-bootstrap", "com/trsvax/bootstrap");
	}

	@Contribute(FrameworkVisitor.class)
	public static void provideBootStrapVisitors(MappedConfiguration<String, FrameworkVisitor> configuration,
			@InjectService(BootstrapFrameworkVisitor.id) FrameworkVisitor fw) {
		configuration.add(BootstrapFrameworkVisitor.id,fw);
	}



	@Contribute(ServiceOverride.class)
	public static void setupApplicationServiceOverrides(MappedConfiguration<Class,Object> configuration, @Local ValidationDecoratorFactory override )
	{
		
		configuration.add(ValidationDecoratorFactory.class, override);
	}

	@Contribute(BeanBlockSource.class)
	public static void provideDefaultBeanBlocks(Configuration<BeanBlockContribution> configuration) {
		addEditBlock(configuration, "image");
		addDisplayBlock(configuration,"image");
	}
	
	private static void addEditBlock(Configuration<BeanBlockContribution> configuration, String dataType) {
        addEditBlock(configuration, dataType, dataType);
    }
	
	private static void addEditBlock(Configuration<BeanBlockContribution> configuration, String dataType, String blockId) {
        configuration.add(new EditBlockContribution(dataType, "tb/AppPropertyEditBlocks", blockId));
    }

	private static void addDisplayBlock(Configuration<BeanBlockContribution> configuration, String dataType)
	{
		addDisplayBlock(configuration, dataType, dataType);
	}

	private static void addDisplayBlock(Configuration<BeanBlockContribution> configuration, String dataType,
			String blockId)
	{
		configuration.add(new DisplayBlockContribution(dataType, "tb/AppPropertyDisplayBlocks", blockId));
	}


}
