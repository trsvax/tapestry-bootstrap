package com.trsvax.bootstrap.environment;

@Environment(environmentInterface=BlockMessageEnvironment.class)
public class BlockMessageValues implements BlockMessageEnvironment {
	private BlockMessageType type;
	
	public BlockMessageValues(BlockMessageEnvironment values) {
		if ( values != null ) {
			type = values.getType();
		}
	}

	public BlockMessageType getType() {
		return type;
	}

	public void setType(BlockMessageType type) {
		this.type = type;
	}

}
