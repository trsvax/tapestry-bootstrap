package com.trsvax.bootstrap.mixins;

import com.trsvax.bootstrap.environment.ButtonType;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.Parameter;

/**
 * Mixin transforming any Tapestry Component as a
 * <a href="http://twitter.github.com/bootstrap/#forms">Bootstrap Button</a>.
 * This mixin adds CSS classes <tt>btn</tt> and <tt>buttonType value</tt>
 * to the component on which it is applied.
 * <br/><br/>
 * Sample use of the mixin:<br/>
 * <code>
 *     &lt;t:pagelink id="myLink" t:page="myPage" t:mixins="tb/button"&gt;My Link Text&lt;/t:pagelink&gt;
 * </code>
 */
public class Button {
    @Parameter(required = false, defaultPrefix = BindingConstants.LITERAL, value = "primary")
    private ButtonType buttonType;

    @BeforeRenderBody
    void addBootstrapCssClasses(MarkupWriter writer) {
        writer.attributes("class", "btn " + buttonType);
    } 
}
