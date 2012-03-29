package com.trsvax.bootstrap.environment;

public class NavValues implements NavEnvironment {
	private boolean inNav = false;
	
	public NavValues(NavEnvironment navValues) {
		if ( navValues != null ) {
			this.inNav = navValues.isInNav();
		}
	}

	public boolean isInNav() {
		return inNav;
	}

	public NavValues withInNav(boolean inNav) {
		this.inNav = inNav;
		return this;
	}
	
	

}
