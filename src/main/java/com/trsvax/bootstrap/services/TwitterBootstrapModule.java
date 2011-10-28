package com.trsvax.bootstrap.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class TwitterBootstrapModule
{
	
    public static void bind(ServiceBinder binder) {
        
    }
    
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("tb", "com.trsvax.bootstrap"));
    }
   
   
}
