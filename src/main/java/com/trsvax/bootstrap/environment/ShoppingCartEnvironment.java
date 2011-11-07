package com.trsvax.bootstrap.environment;

import java.util.List;

public interface ShoppingCartEnvironment<T> {
	public static final String items = "env:com.trsvax.bootstrap.environment.ShoppingCartEnvironment.items";
	public static final String total = "env:com.trsvax.bootstrap.environment.ShoppingCartEnvironment.total";
	public static final String include = "env:com.trsvax.bootstrap.environment.ShoppingCartEnvironment.include";

	public List<T> getItems();
	public String getTotal();
	public String getInclude();

}
