package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class FormValues implements FormEnvironment {
	private boolean isInstrumented;
	private String type;
	private String prefix = "form";
	
	public FormValues(FormEnvironment values) {
		if ( values != null ) {
			this.type = values.getType(null);
		}
	}

	public boolean isInstrumented() {
		return isInstrumented;
	}

	public void withInstrumented(boolean value) {
		isInstrumented = value;
	}

	public String getType(FrameworkMixin mixin) {
		if ( mixin == null ) {
			return type;
		}
		return mixin.getType() == null ? type : mixin.getType();
	}
	
	public FormValues withType(String type) {
		this.type = type;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}

}
