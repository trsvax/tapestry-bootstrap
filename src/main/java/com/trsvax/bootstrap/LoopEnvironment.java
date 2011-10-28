package com.trsvax.bootstrap;

import java.util.List;

public interface LoopEnvironment<T> {
	
	public List<T> getSource();
	public void setSource(List<T> source);

}