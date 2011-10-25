package com.trsvax.bootstrap.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class TwitterBootstrapModule
{
	public static final String ID = "com.trsvax.bootstrap";
	private static final String prefix = "default:" + ID + ".";
	public static final String  PaginationRowsPerPage = prefix + "Pagination.RowsPerPage";
	public static final String  PaginationRange = prefix + "Pagination.Range";
	
    public static void bind(ServiceBinder binder)
    {
        
    }
    
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("tb", "com.trsvax.bootstrap"));
    }
    
    public static void contributeFactoryDefaults(
            MappedConfiguration<String, String> configuration) {

        configuration.add(PaginationRowsPerPage, "5");
        configuration.add(PaginationRange, "5");
    }
    
    @Contribute(ComponentClassTransformWorker2.class)
    @Primary
    public static void  addWorker(OrderedConfiguration<ComponentClassTransformWorker2> configuration) {
    	configuration.addInstance("ComponentRequestParameterWorker", ComponentRequestParameterWorker.class);
    }
   
}
