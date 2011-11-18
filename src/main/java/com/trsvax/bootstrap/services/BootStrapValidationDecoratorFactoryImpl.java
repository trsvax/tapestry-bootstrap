package com.trsvax.bootstrap.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.ValidationDecoratorFactory;
import org.slf4j.Logger;

public class BootStrapValidationDecoratorFactoryImpl implements ValidationDecoratorFactory {
	private final Environment environment;
	private final Logger logger;
	
	public BootStrapValidationDecoratorFactoryImpl(Environment environment, Logger logger) {
		this.environment = environment;
		this.logger = logger;
		logger.info("bootstrap decorator");
	}

	public ValidationDecorator newInstance(MarkupWriter writer) {
		return new BootStrapValidationDecorator(writer,environment,logger);
	}

}
