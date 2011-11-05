package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet={"classpath:/com/trsvax/bootstrap/bootstrap.css"},
library={"classpath:/com/trsvax/bootstrap/bootstrap-scrollspy.js","classpath:/com/trsvax/bootstrap/bootstrap-twipsy.js"})
public class ContainerLayout {
	
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private Block sidebar;

}
