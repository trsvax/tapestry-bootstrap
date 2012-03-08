package com.trsvax.bootstrap.environment;

import java.util.List;

public interface LoopEnvironment<T> {
	public static final String source = "env:com.trsvax.bootstrap.environment.LoopEnvironment.source";
	
	public List<T> getSource();
	public boolean isBound();
	public List<T> bindSource();
}