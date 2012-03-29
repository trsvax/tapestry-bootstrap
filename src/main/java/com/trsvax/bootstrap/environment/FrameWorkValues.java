package com.trsvax.bootstrap.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FrameWorkValues implements FrameWorkEnvironment {
	private String name = "";
	private Set<String> excludes = new HashSet<String>();
	private Map<String,String> scripts = new HashMap<String, String>();
	
	public FrameWorkValues(FrameWorkValues values) {
		if ( values != null ) {
			excludes = values.getExcludes();
		}

	}
	
	public FrameWorkValues withName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public Set<String> getExcludes() {
		return excludes;
	}
	

	public FrameWorkValues addExclude(String pattern) {
		excludes.add(pattern);
		return this;
		
	}

	public void addScriptOnce(String script) {
		scripts.put(script, null);
		
	}

	public Set<Entry<String, String>> getOnceScripts() {
		return scripts.entrySet();
	}

}
