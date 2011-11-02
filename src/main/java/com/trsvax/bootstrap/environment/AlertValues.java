package com.trsvax.bootstrap.environment;

public class AlertValues implements AlertEnvironment {
	private AlertType type;
	
	public AlertValues(AlertEnvironment values) {
		if ( values != null ) {
			type = values.getType();
		}
	}

	public AlertType getType() {
		return type;
	}

	public void setType(AlertType type) {
		this.type = type;
	}

}
