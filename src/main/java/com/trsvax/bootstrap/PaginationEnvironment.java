package com.trsvax.bootstrap;

import org.apache.tapestry5.Link;

public interface PaginationEnvironment {

	public abstract Integer getRowsPerPage();
	public abstract void setRowsPerPage(Integer rowsPerPage);

	public abstract Integer getItemCount();
	public abstract void setItemCount(Integer itemCount);

	public abstract void setCurrentPage(Integer currentPage);
	public abstract Integer getCurrentPage();

	public abstract Integer getRange();
	public abstract void setRange(Integer range);

	public abstract Link getLink(Integer count);

}