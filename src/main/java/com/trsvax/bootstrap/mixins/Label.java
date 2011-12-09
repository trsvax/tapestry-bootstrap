package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.LabelEnvironment;

/**
 * Mixin transforming any Tapestry Component as a
 * <a href="http://twitter.github.com/bootstrap/#forms">Bootstrap Label</a>.
 * This mixin adds CSS classes <tt>btn</tt>, <tt>labelType value</tt>
 * to the component on which it is applied.
 * <br/><br/>
 * Sample use of the mixin:<br/>
 * <code>
 *     &lt;t:pagelink id="myLink" t:page="myPage" t:mixins="tb/label"&gt;My Link Text&lt;/t:pagelink&gt;
 * </code>
 */
public class Label extends BootstrapMixin {
	
	@Parameter(value=LabelEnvironment.labelType,defaultPrefix="literal")
	private String labelType;
	
	@BeforeRenderBody
    void addBootstrapCssClasses(MarkupWriter writer) {
        writer.attributes("class", toClassName("label",labelType));
    } 

}
