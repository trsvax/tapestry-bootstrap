package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class BeanDisplayValues implements BeanDisplayEnvironment {
	private boolean isInstrumented;
	private String type;
	private String prefix = "dl";
	
	public BeanDisplayValues(BeanDisplayEnvironment values) {
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
	
	public BeanDisplayValues withType(String type) {
		this.type = type;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}

}
