package com.trsvax.bootstrap.services;

import org.apache.tapestry5.dom.Visitor;

import com.trsvax.bootstrap.environment.BootstrapEnvironment;

public interface ExcludeVisitor {

	public Visitor visit(BootstrapEnvironment values);
}
