package com.trsvax.bootstrap.environment;

@Environment(environmentInterface=BlockMessageEnvironment.class)
public class BlockMessageValues implements BlockMessageEnvironment {
	private BlockMessageType type;

	public BlockMessageType getType() {
		return type;
	}

	public void setType(BlockMessageType type) {
		this.type = type;
	}

}
