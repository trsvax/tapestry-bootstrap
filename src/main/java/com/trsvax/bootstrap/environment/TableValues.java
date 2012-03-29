package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class TableValues implements TableEnvironment {
	private String type;
	private boolean isInstrumented;
	
	public TableValues(TableEnvironment values) {
		if ( values != null ) {
			
		}
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
	
	public TableValues withType(String type) {
		this.type = type;
		return this;
	}

}
