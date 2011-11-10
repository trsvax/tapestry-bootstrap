package com.trsvax.bootstrap.environment;

import java.util.List;

public interface ExcludeEnvironment {
	
	public List<String> getExcludes(String mode);
	public void addExclude(String mode, String pattern);	

}
