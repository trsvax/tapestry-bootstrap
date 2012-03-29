package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class ButtonGroupValues implements ButtonGroupEnvironment {
	private boolean isInstrumented = true;
	private boolean isButtonGroup;
	private boolean isDropDown;
	private String type;
	
	public ButtonGroupValues(ButtonGroupValues values) {
		
	}
	
	public ButtonGroupValues withButtonGroup(boolean isButtonGroup) {
		this.isButtonGroup = isButtonGroup;
		return this;
	}

	public boolean isButtonGroup() {
		return isButtonGroup;
	}

	public void setButtonGroup(boolean isButtonGroup) {
		this.isButtonGroup = isButtonGroup;
	}

	public boolean isDropDown() {
		return isDropDown;
	}

	public void setDropDown(boolean isDropDown) {
		this.isDropDown = isDropDown;
	}

	public boolean isInstrumented() {
		return isInstrumented;
	}
	
	public void withInstrumented(boolean value) {
		this.isInstrumented = value;
	}

	public String getType(FrameworkMixin mixin) {
		return mixin.getType() == null ? type : mixin.getType();
	}

}
