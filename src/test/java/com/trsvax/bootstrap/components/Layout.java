package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.annotations.Exclude;

@Exclude(stylesheet={"core"})
@Import(stylesheet={
		"classpath:/com/trsvax/bootstrap/assets/bootstrap/css/bootstrap.css",
		"classpath:/com/trsvax/bootstrap/assets/bootstrap/css/bootstrap-responsive.css"
		},
library={
		"classpath:/com/trsvax/bootstrap/assets/bootstrap/js/bootstrap.js",
		"classpath:/com/trsvax/bootstrap/assets/components/datepicker/datefield.js",
		"classpath:/com/trsvax/bootstrap/assets/validation.js"
		}
)
public class Layout  {
	
	@Parameter
	@Property
	private Block subnav;
	
	@Parameter(defaultPrefix="literal")
	@Property
	private String title;

}

