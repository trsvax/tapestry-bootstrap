package com.trsvax.bootstrap.environment;

import java.util.Map.Entry;
import java.util.Set;

public interface FrameworkEnvironment {
	
	public Set<String> getExcludes();
	public FrameworkEnvironment addExclude(String pattern);	
	
	public void addScriptOnce(String script);
	public Set<Entry<String, String>> getOnceScripts();
	public String getName();

}
