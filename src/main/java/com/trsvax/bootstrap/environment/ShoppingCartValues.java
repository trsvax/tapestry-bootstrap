package com.trsvax.bootstrap.environment;

import java.util.List;

public class ShoppingCartValues<T> implements ShoppingCartEnvironment<T> {
	private List<T> items;
	private String total;
	private String include;
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}

	
}
