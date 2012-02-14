package com.trsvax.bootstrap.services;

import org.apache.tapestry5.dom.Visitor;

import com.trsvax.bootstrap.environment.ExcludeEnvironment;

public interface ExcludeVisitor {

	public Visitor visit(ExcludeEnvironment values);
}
