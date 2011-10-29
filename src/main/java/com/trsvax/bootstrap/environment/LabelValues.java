package com.trsvax.bootstrap.environment;

@Environment(environmentInterface=LabelEnvironment.class)
public class LabelValues implements LabelEnvironment {
	private LabelType type;

	public LabelType getType() {
		return type;
	}

	public void setType(LabelType type) {
		this.type = type;
	}



}
