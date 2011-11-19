package com.trsvax.bootstrap.services;

import org.apache.tapestry5.BaseValidationDecorator;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.services.Environment;

public class BootStrapValidationDecorator extends BaseValidationDecorator {
	private final MarkupWriter markupWriter;
	private final Environment environment;

	public BootStrapValidationDecorator(MarkupWriter markupWriter,
			Environment environment) {
		this.markupWriter = markupWriter;
		this.environment = environment;
	}

	@Override
	public void beforeField(Field field) {
		if (inError(field)) {
			markupWriter.getElement().getContainer().addClassName("error");
		}
	}

	@Override
	public void insideField(Field field) {
		if (inError(field)) {
			markupWriter.getElement().addClassName("error");
		}

	}

	@Override
	public void afterField(Field field) {
		if (inError(field)) {
			markupWriter.element("span", "class", "help-inline");
			markupWriter.write(getError(field));
			markupWriter.end();
		}
	}

	private boolean inError(Field field) {
		ValidationTracker tracker = environment
				.peekRequired(ValidationTracker.class);
		return tracker.inError(field);
	}

	private String getError(Field field) {
		ValidationTracker tracker = environment
				.peekRequired(ValidationTracker.class);

		return tracker.getError(field);
	}

}
