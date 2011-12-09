package com.trsvax.bootstrap.mixins;

public class BootstrapMixin {
	
    String toClassName(String ... args) {
    	StringBuilder builder = new StringBuilder();
    	for ( String arg : args ) {
    		if ( arg != null && arg.trim().length() > 0 ) {
    			builder.append(arg).append(" ");
    		}   		
    	}   	
    	return builder.toString().trim();
    }

}
