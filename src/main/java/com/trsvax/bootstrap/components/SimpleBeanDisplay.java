 package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.corelib.components.BeanDisplay;
import org.apache.tapestry5.ioc.annotations.Inject;

public class SimpleBeanDisplay extends BeanDisplay {
	
	@Inject
	private Block mediaTitle;
	
	public Object getPropertyBlock() {
		return mediaTitle;
	}

}
