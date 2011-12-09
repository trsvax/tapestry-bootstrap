package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.bootstrap.environment.ButtonEnvironment;

/**
 * Mixin transforming any Tapestry Component as a
 * <a href="http://twitter.github.com/bootstrap/#forms">Bootstrap Button</a>.
 * This mixin adds CSS classes <tt>btn</tt>, <tt>buttonType value</tt> and <tt>buttonSize value</tt>
 * to the component on which it is applied.
 * <br/><br/>
 * Sample use of the mixin:<br/>
 * <code>
 *     &lt;t:pagelink id="myLink" t:page="myPage" t:mixins="tb/button"&gt;My Link Text&lt;/t:pagelink&gt;
 * </code>
 */
public class Button extends BootstrapMixin {
    @Parameter(value = ButtonEnvironment.buttonType, defaultPrefix = BindingConstants.LITERAL, required = false)
    private String buttonType;

    @Parameter(value = ButtonEnvironment.buttonSize, defaultPrefix = BindingConstants.LITERAL, required = false)
    private String buttonSize;

    @BeforeRenderBody
    void addBootstrapCssClasses(MarkupWriter writer) {
        writer.attributes("class", toClassName("btn",buttonType,buttonSize));
    } 
    

}
