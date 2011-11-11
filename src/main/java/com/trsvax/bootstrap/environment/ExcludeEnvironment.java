package com.trsvax.bootstrap.environment;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface ExcludeEnvironment {
	
	public List<String> getExcludes(String mode);
	public void addExclude(String mode, String pattern);	
	
	public void addScriptOnce(String script);
	public Set<Entry<String, String>> getOnceScripts();

}
