package com.trsvax.bootstrap.environment;

import java.util.Map.Entry;
import java.util.Set;

public interface BootstrapEnvironment {
	
	public Set<String> getExcludes();
	public void addExclude(String pattern);	
	
	public void addScriptOnce(String script);
	public Set<Entry<String, String>> getOnceScripts();

}
