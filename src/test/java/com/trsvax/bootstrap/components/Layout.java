package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.annotations.Exclude;

@Exclude(stylesheet={"core"})
@Import(stylesheet={
		"classpath:/com/trsvax/bootstrap/pages/twitter/css/bootstrap.css",
		"context:assets/css/bootstrap-responsive.css",
		"context:assets/css/docs.css",
		"context:assets/js/google-code-prettify/prettify.css"
		},
library={
		"context:assets/js/google-code-prettify/prettify.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-transition.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-alert.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-modal.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-scrollspy.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-tab.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-tooltip.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-popover.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-collapse.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-carousel.js",
		"classpath:/com/trsvax/bootstrap/pages/twitter/js/bootstrap-typeahead.js",
		"context:assets/js/application.js"
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

