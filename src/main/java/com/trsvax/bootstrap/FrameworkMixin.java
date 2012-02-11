package com.trsvax.bootstrap;

import java.util.Map;

import org.apache.tapestry5.ComponentResources;

public interface FrameworkMixin {

	public ComponentResources getComponentResources();
	public Map<String,String> getParms();
}
