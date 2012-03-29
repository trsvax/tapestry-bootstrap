package com.trsvax.bootstrap.environment;

import java.util.Map.Entry;
import java.util.Set;

public interface FrameWorkEnvironment {
	
	public String getName();
	
	public Set<String> getExcludes();
	public FrameWorkEnvironment addExclude(String pattern);	
	
	public void addScriptOnce(String script);
	public Set<Entry<String, String>> getOnceScripts();

}
