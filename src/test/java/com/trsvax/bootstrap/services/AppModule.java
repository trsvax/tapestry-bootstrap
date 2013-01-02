package com.trsvax.bootstrap.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

@SubModule(BootstrapModule.class)
public class AppModule {
	

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration) {
    	configuration.add(JQuerySymbolConstants.SUPPRESS_PROTOTYPE, "false");
    	configuration.add(JQuerySymbolConstants.JQUERY_ALIAS, "$j");
    }

}
