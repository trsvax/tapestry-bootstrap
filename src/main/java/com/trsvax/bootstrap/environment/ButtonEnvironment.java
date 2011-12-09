package com.trsvax.bootstrap.environment;

public interface ButtonEnvironment {
	public static final String buttonSize = "env:com.trsvax.bootstrap.environment.ButtonEnvironment.buttonSize";
	public static final String buttonType = "env:com.trsvax.bootstrap.environment.ButtonEnvironment.buttonType";

	public ButtonSize getButtonSize();
	public ButtonType getButtonType();
}
