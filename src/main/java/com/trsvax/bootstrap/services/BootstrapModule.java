package com.trsvax.bootstrap.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockSource;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

import com.trsvax.bootstrap.environment.AlertsEnvironment;
import com.trsvax.bootstrap.environment.AlertsValues;
import com.trsvax.bootstrap.environment.BeanDisplayEnvironment;
import com.trsvax.bootstrap.environment.BeanDisplayValues;
import com.trsvax.bootstrap.environment.BreadcrumbEnvironment;
import com.trsvax.bootstrap.environment.BreadcrumbValues;
import com.trsvax.bootstrap.environment.ButtonEnvironment;
import com.trsvax.bootstrap.environment.ButtonValues;
import com.trsvax.bootstrap.environment.FormEnvironment;
import com.trsvax.bootstrap.environment.FormValues;
import com.trsvax.bootstrap.environment.FrameworkEnvironment;
import com.trsvax.bootstrap.environment.FrameworkValues;
import com.trsvax.bootstrap.environment.GridPagerEnvironment;
import com.trsvax.bootstrap.environment.GridPagerValues;
import com.trsvax.bootstrap.environment.NavEnvironment;
import com.trsvax.bootstrap.environment.NavValues;
import com.trsvax.bootstrap.environment.TableEnvironment;
import com.trsvax.bootstrap.environment.TableValues;


/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class BootstrapModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(BindingFactory.class,SessionBindingFactory.class).withId("SessionBindingFactory");
		binder.bind(BindingFactory.class,EnvironmentBindingFactory.class).withId("EnvironmentBindingFactory");
		binder.bind(StringTemplateParser.class,StringTemplateParserImpl.class);

		binder.bind(EnvironmentSetup.class, EnvironmentSetupImpl.class);


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
	} 
	
	@Contribute(EnvironmentSetup.class)
	public static void provideEnvironmentSetup(MappedConfiguration<Class, Object> configuration) {
		configuration.add(FrameworkEnvironment.class, new FrameworkValues(null).withName("tb"));
		configuration.add(BreadcrumbEnvironment.class,new BreadcrumbValues(null));
		configuration.add(ButtonEnvironment.class, new ButtonValues(null));
		configuration.add(FormEnvironment.class, new FormValues(null));
		configuration.add(NavEnvironment.class, new NavValues(null));
		configuration.add(GridPagerEnvironment.class, new GridPagerValues(null));
		configuration.add(TableEnvironment.class, new TableValues(null));
		configuration.add(BeanDisplayEnvironment.class, new BeanDisplayValues(null));
		configuration.add(AlertsEnvironment.class, new AlertsValues(null));
	}

	
	

	public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration) {
		configuration.add("tap-bootstrap", "com/trsvax/bootstrap");
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

	private static void addDisplayBlock(Configuration<BeanBlockContribution> configuration, String dataType) {
		addDisplayBlock(configuration, dataType, dataType);
	}

	private static void addDisplayBlock(Configuration<BeanBlockContribution> configuration, String dataType, String blockId) {
		configuration.add(new DisplayBlockContribution(dataType, "tb/AppPropertyDisplayBlocks", blockId));
	}


}
