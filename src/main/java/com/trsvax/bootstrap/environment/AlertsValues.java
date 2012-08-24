package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class AlertsValues implements AlertsEnvironment {
	private boolean isInstrumented;
	private String type;
	private String prefix = "alert";
	
	public AlertsValues(AlertsEnvironment values) {
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
	
	public AlertsValues withType(String type) {
		this.type = type;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}


}
