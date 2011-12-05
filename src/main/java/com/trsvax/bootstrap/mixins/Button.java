package com.trsvax.bootstrap.mixins;

import com.trsvax.bootstrap.environment.ButtonEnvironment;
import com.trsvax.bootstrap.environment.ButtonType;
import org.apache.commons.lang.StringUtils;
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
    @Parameter(value = ButtonEnvironment.type, defaultPrefix = BindingConstants.LITERAL, required = false)
    private String buttonType;

    @Parameter(value = ButtonEnvironment.size, defaultPrefix = BindingConstants.LITERAL, required = false)
    private String size;

    @BeforeRenderBody
    void addBootstrapCssClasses(MarkupWriter writer) {
        StringBuilder builder = new StringBuilder("btn");
        if (StringUtils.isNotBlank(buttonType))
            builder.append(' ').append(buttonType);
        if (StringUtils.isNotBlank(size))
            builder.append(' ').append(size);
        writer.attributes("class", builder.toString());
    } 
}
