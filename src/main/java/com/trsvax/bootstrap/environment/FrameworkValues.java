package com.trsvax.bootstrap.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FrameworkValues implements FrameworkEnvironment {
	private String name;
	private Set<String> excludes = new HashSet<String>();
	private Map<String,String> scripts = new HashMap<String, String>();
	
	public FrameworkValues(FrameworkValues values) {
		if ( values != null ) {
			excludes = values.getExcludes();
		}

	}

	public Set<String> getExcludes() {
		return excludes;
	}
	

	public FrameworkValues addExclude(String pattern) {
		excludes.add(pattern);
		return this;
		
	}

	public void addScriptOnce(String script) {
		scripts.put(script, null);
		
	}

	public Set<Entry<String, String>> getOnceScripts() {
		return scripts.entrySet();
	}

	public FrameworkValues withName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}

}
