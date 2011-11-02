package com.trsvax.bootstrap.environment;


@Environment(environmentInterface=ButtonEnvironment.class)
public class ButtonValues implements ButtonEnvironment {
	private ButtonType type;
	private ButtonSize size;
	
	public ButtonValues(ButtonEnvironment values) {
		if ( values != null ) {
			type = values.getType();
			size = values.getSize();
		}
	}
	
	public ButtonType getType() {
		return type;
	}
	public void setType(ButtonType type) {
		this.type = type;
	}
	public ButtonSize getSize() {
		return size;
	}
	public void setSize(ButtonSize size) {
		this.size = size;
	}

}
