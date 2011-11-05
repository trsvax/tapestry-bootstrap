package com.trsvax.bootstrap.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

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
    } 
   
   
}
