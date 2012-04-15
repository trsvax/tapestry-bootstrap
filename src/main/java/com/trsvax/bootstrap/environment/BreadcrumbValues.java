package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class BreadcrumbValues implements BreadcrumbEnvironment {
	private boolean isInstrumented = true;
	private String type = "";
	private String prefix = "";
	
	public BreadcrumbValues(BreadcrumbEnvironment values) {
		
	}

	public boolean isInstrumented() {
		return isInstrumented;
	}

	public void withInstrumented(boolean value) {
		isInstrumented = value;
	}

	public String getType(FrameworkMixin mixin) {
		return mixin.getType() == null ? type : mixin.getType();
	}
	
	public BreadcrumbValues withType(String type) {
		this.type = type;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}

}
