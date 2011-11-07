package com.trsvax.bootstrap.components;

import java.util.List;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.environment.ShoppingCartEnvironment;

@SuppressWarnings("unused")
public class ShoppingCart<T> {

	@Parameter(value=ShoppingCartEnvironment.items,required=true)
	@Property
	private List<T> items;
	
	@Parameter(value=ShoppingCartEnvironment.include)
	@Property
	private String include;
	
	@Parameter(value=ShoppingCartEnvironment.total,required=true)
	@Property
	private String total;

}
