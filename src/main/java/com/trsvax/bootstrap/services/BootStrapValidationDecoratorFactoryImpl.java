package com.trsvax.bootstrap.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.ValidationDecoratorFactory;
import org.slf4j.Logger;

public class BootStrapValidationDecoratorFactoryImpl implements ValidationDecoratorFactory {
	private final Environment environment;
	
	public BootStrapValidationDecoratorFactoryImpl(Environment environment) {
		this.environment = environment;
	}

	public ValidationDecorator newInstance(MarkupWriter writer) {
		return new BootStrapValidationDecorator(writer,environment);
	}

}
