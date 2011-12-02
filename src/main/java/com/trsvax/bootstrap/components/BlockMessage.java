package com.trsvax.bootstrap.components;

import com.trsvax.bootstrap.environment.BlockMessageEnvironment;
import com.trsvax.bootstrap.environment.BlockMessageType;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;


/**
 * Display a <a href="http://twitter.github.com/bootstrap/#alerts">Bootstrap block message</a>.
 * @tapestrydoc
 */
@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-alerts.js")
public class BlockMessage extends BootstrapComponent {

    /**
     * Type of block message: any value of the {@link BlockMessageType} enum, that is <tt>warning</tt>, <tt>error</tt>,
     * <tt>info</tt> or <tt>success</tt>
     * */
	@Parameter(value=BlockMessageEnvironment.type,defaultPrefix="literal")
	private String type;

    /** A {@link Block} to which the actions should be delegated to. */
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private Block actions;
	
	public String getType() {
		return formatClass(type);
	}	
}
