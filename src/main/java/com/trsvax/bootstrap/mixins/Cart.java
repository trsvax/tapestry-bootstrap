package com.trsvax.bootstrap.mixins;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;

import com.trsvax.bootstrap.environment.ShoppingCartEnvironment;
import com.trsvax.bootstrap.environment.ShoppingCartValues;
import com.trsvax.bootstrap.services.CartDAO;

public class Cart<T> {
	
	@Parameter(required=true)
	private CartDAO<T> cart;
	
	@Property
	private String include;
	
	@Inject
	private Environment environment;
	
	@SetupRender
	void setupRender() {
		ShoppingCartValues<T> values = new ShoppingCartValues<T>();	
		values.setTotal(cart.getTotal());
		values.setItems(cart.getItems());
		values.setInclude(include);
		environment.push(ShoppingCartEnvironment.class, values);		
	}
	
	@AfterRender
	void afterRender() {
		environment.pop(ShoppingCartEnvironment.class);
	}
	
   void onEmpty() {
    	cart.empty();
    }
}
