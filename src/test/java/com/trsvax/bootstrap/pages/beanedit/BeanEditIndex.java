package com.trsvax.bootstrap.pages.beanedit;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.Address;

@Import(library="classpath:/com/trsvax/bootstrap/validation.js")
public class BeanEditIndex {
	
	@PageActivationContext
	@Property
	private String formtype;

	@Property
	private Address address;
}
