package com.trsvax.bootstrap.environment;

import com.trsvax.bootstrap.FrameworkMixin;

public class TableValues implements TableEnvironment {
	private String type;
	private boolean isInstrumented;
	private String sortElement = "i";
	private String[] sortElementAttributes = {"class","icon-random"};
	private String prefix = "table";
	
	public TableValues(TableEnvironment values) {
		if ( values != null ) {
			type = values.getType(null);
			sortElement = values.getSortElement();
			sortElementAttributes = values.getSortElementAttributes();
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

	public String getSortElement() {
		return sortElement;
	}

	public TableValues withSortElement(String element) {
		this.sortElement = element;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}

	public String[] getSortElementAttributes() {
		return sortElementAttributes;
	}
	
	public TableValues withSortElementAttributes(String[] value) {
		sortElementAttributes = value;
		return this;
	}

}
