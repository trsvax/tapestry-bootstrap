package com.trsvax.bootstrap.components;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Display a list of beans in a table.<br/>
 * @tapestrydoc
 * @see BeanDisplay
 * @see BeanDisplayLabel
 * @see Pagination
 * @see <a href="http://trsvax.com/blog/BeanGrid">Blog post</a>
 * @see <a href="http://twitter.github.com/bootstrap/#tables">Bootstrap Tables</a>
 */
@SupportsInformalParameters
@SuppressWarnings("unused")
public class BeanGrid<T> extends BootstrapComponent {
    /**
     * The source of data for the BeanGrid to display.
     */
	@Parameter(autoconnect=true,required=true,allowNull=false)
	@Property
	private List<?> source;

    /**
     * Used to store the current object being rendered (for the current row).
     */
	@Parameter
	@Property
	private Object value;
	
	@Parameter(value="resources.id")
	@Property
	private String parameterName;
	
	@Parameter
	@Property
	private Integer index;
	
	@Inject
	@Property
	private ComponentResources resources;
	
	@Property
	private String tableType;
	
	@Component(parameters={"value=value","index=index"})
	private Loop<T> loop;
	
	@SetupRender
	private void setupRender() {
		value = source.get(0);
		tableType = resources.getInformalParameter("class", String.class);
	}

}
