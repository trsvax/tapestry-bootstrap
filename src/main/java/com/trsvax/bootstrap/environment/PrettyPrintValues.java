package com.trsvax.bootstrap.environment;

@Environment(environmentInterface=PrettyPrintEnvironment.class)
public class PrettyPrintValues implements PrettyPrintEnvironment {
	private PrettyPrintLanguage language;

	public PrettyPrintLanguage getLanguage() {
		return language;
	}

	public void setLanguage(PrettyPrintLanguage language) {
		this.language = language;
	}

}
