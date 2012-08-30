package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.internal.DefaultValidationDecorator;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.ValidationDecoratorFactory;

public class BootStrapValidationDecoratorFactoryImpl implements ValidationDecoratorFactory {
	private final Environment environment;
	private final Asset spacerImage;
	
	public BootStrapValidationDecoratorFactoryImpl(Environment environment, @Path("${tapestry.spacer-image}")
    Asset spacerImage) {
		this.environment = environment;
		this.spacerImage = spacerImage;
	}

	public ValidationDecorator newInstance(MarkupWriter writer) {
		DefaultValidationDecorator decorator = new DefaultValidationDecorator(environment, spacerImage, writer);
		//return new BootStrapValidationDecorator(writer,environment);
		
		return decorator;
	}

}
