package com.trsvax.bootstrap.environment;

public class RowEnvironmentImpl implements RowEnvironment {
	
	private Integer span = 0;
	private Integer offset = 0;

	public Integer addSpan(Integer span) {
		if ( span != null ) {
			this.span += span;
		}
		check();
		return this.span;
	}

	public Integer addOffset(Integer offset) {
		if ( offset != null ) {
			this.offset += offset;
		}
		check();
		return this.offset;
	}
	
	void check() {
		if ( span + offset > 12 ) {
			throw new RuntimeException(String.format("Row larger than 12 span = %d, offset = %d",span,offset));
		}
	}

}
