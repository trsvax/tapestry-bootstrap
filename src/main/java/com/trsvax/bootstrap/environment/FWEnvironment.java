package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public interface FWEnvironment {
	
	public boolean isInstrumented();
	public void withInstrumented(boolean value);
	public String getType(FrameworkMixin mixin);
	public String getPrefix();

}
