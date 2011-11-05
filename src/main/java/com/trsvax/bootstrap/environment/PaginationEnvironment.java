package com.trsvax.bootstrap.environment;

import org.apache.tapestry5.Link;

public interface PaginationEnvironment {
	public final static String rowsPerPage = "env:com.trsvax.bootstrap.environment.PaginationEnvironment.rowsPerPage";
	public final static String itemCount = "env:com.trsvax.bootstrap.environment.PaginationEnvironment.itemCount";
	public final static String currentPage = "env:com.trsvax.bootstrap.environment.PaginationEnvironment.currentPage";
	public final static String range = "env:com.trsvax.bootstrap.environment.PaginationEnvironment.range";

	public abstract Integer getRowsPerPage();

	public abstract Integer getItemCount();

	public abstract Integer getCurrentPage();

	public abstract Integer getRange();

	public abstract Link getLink(Integer count);

}