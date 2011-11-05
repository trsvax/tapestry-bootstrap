package com.trsvax.bootstrap.environment;

public interface ButtonEnvironment {
	public static final String size = "env:com.trsvax.bootstrap.environment.ButtonEnvironment.size";
	public static final String type = "env:com.trsvax.bootstrap.environment.ButtonEnvironment.type";

	public ButtonSize getSize();
	public ButtonType getType();
}
