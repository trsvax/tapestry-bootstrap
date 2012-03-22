package com.trsvax.bootstrap.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BootstrapValues implements BootstrapEnvironment {
	private Set<String> excludes = new HashSet<String>();
	private Map<String,String> scripts = new HashMap<String, String>();
	
	public BootstrapValues() {
		
	}

	public Set<String> getExcludes() {
		return excludes;
	}
	

	public void addExclude(String pattern) {
		excludes.add(pattern);
	}

	public void addScriptOnce(String script) {
		scripts.put(script, null);
		
	}

	public Set<Entry<String, String>> getOnceScripts() {
		return scripts.entrySet();
	}

}
