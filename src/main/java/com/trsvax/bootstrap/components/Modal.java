package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

@SuppressWarnings("unused")
public class Modal extends BootstrapComponent {
	

	@Parameter
	@Property
	private String id;

	@Parameter
	@Property
	private Block header;
	
	@Parameter
	@Property
	private Block footer;
}
