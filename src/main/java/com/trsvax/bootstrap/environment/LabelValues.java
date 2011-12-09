package com.trsvax.bootstrap.environment;

@Environment(environmentInterface=LabelEnvironment.class)
public class LabelValues implements LabelEnvironment {
	private LabelType labelType;
	
	public LabelValues(LabelEnvironment values) {
		if ( values != null ) {
			labelType = values.getLabelType();
		}
	}

	public LabelType getLabelType() {
		return labelType;
	}

	public void setLabelType(LabelType type) {
		this.labelType = type;
	}

}
