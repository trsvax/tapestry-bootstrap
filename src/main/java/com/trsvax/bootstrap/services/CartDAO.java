package com.trsvax.bootstrap.services;

import java.util.List;

public interface CartDAO<T> {
	
	public void add(T item);
	public List<T> getItems();
	public void empty();
	
	public String getTotal();

}
