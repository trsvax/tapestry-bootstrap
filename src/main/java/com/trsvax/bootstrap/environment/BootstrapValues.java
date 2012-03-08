package com.trsvax.bootstrap.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BootstrapValues implements BootstrapEnvironment {
	private Map<String,List<String>> excludeMap = new HashMap<String, List<String>>();
	private Map<String,String> scripts = new HashMap<String, String>();
	
	public BootstrapValues() {
		excludeMap.put("ALL",new ArrayList<String>());
	}

	public List<String> getExcludes(String mode) {
		if ( mode == null ) {
			return excludeMap.get("ALL");
		}
		List<String> excludes =  excludeMap.get(mode);
		if ( excludes == null ) {
			excludes = excludeMap.get("ALL");
		} else {
			excludes.addAll(excludeMap.get("ALL"));
		}
		return excludes;
	}
	

	public void addExclude(String mode, String pattern) {
		if ( mode == null ) {
			mode = "ALL";
		}
		List<String> excludes = excludeMap.get(mode);
		if ( excludes == null ) {
			excludes = new ArrayList<String>();
			excludeMap.put(mode, excludes);
		}
		excludes.add(pattern);
	}

	public void addScriptOnce(String script) {
		scripts.put(script, null);
		
	}

	public Set<Entry<String, String>> getOnceScripts() {
		return scripts.entrySet();
	}

}
