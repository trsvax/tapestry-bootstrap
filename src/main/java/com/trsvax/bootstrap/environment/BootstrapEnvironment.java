package com.trsvax.bootstrap.environment;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public interface BootstrapEnvironment {
	
	public List<String> getExcludes(String mode);
	public void addExclude(String mode, String pattern);	
	
	public void addScriptOnce(String script);
	public Set<Entry<String, String>> getOnceScripts();

}
