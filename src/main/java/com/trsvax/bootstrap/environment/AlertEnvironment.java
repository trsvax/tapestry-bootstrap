package com.trsvax.bootstrap.environment;

public interface AlertEnvironment {
	public final static String type = "env:com.trsvax.bootstrap.environment.AlertEnvironment.type";

	public AlertType getType();

}
