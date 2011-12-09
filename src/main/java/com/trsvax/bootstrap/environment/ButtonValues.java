package com.trsvax.bootstrap.environment;


@Environment(environmentInterface=ButtonEnvironment.class)
public class ButtonValues implements ButtonEnvironment {
	private ButtonType buttonType;
	private ButtonSize buttonSize;
	
	public ButtonValues(ButtonEnvironment values) {
		if ( values != null ) {
			buttonType = values.getButtonType();
			buttonSize = values.getButtonSize();
		}
	}
	
	public ButtonType getButtonType() {
		return buttonType;
	}
	public void setButtonType(ButtonType type) {
		this.buttonType = type;
	}
	public ButtonSize getButtonSize() {
		return buttonSize;
	}
	public void setButtonSize(ButtonSize size) {
		this.buttonSize = size;
	}

}
