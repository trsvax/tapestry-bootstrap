package com.trsvax.bootstrap.pages.beanedit;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.Address;

@Import(library="classpath:/com/trsvax/bootstrap/validation.js")
public class BeanEditIndex {

	@Property
	private Address address;
}
