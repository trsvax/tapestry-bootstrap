package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;

/*
 * From SlimerDude via Tapestry email list
 */
public class IfIE { 

    @Parameter(required=true, defaultPrefix="literal") 
    private String test; 

    public void beginRender(MarkupWriter writer) { 
        // write out a dummy tag to prevent T5 from writing it's script tags 'inside' the IE comment! 
        writer.element("script", "type", "text/javascript"); 
        writer.end(); 

        writer.writeRaw("\n<!--[if "); 
        writer.writeRaw(test); 
        writer.writeRaw("]>\n"); 
    } 

    public void afterRender(MarkupWriter writer) { 
        writer.writeRaw("\n<![endif]-->\n"); 
    } 
} 
