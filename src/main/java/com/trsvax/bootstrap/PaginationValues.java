package com.trsvax.bootstrap;



public abstract class PaginationValues implements PaginationEnvironment, Cloneable {
	private Integer itemCount;
	private Integer rowsPerPage = 6;
	private Integer currentPage;
	private Integer range = 6;

	
	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#getRowsPerPage()
	 */
	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#setRowsPerPage(java.lang.Integer)
	 */
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	
	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#getItemCount()
	 */
	public Integer getItemCount() {
		return itemCount;
	}
	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#setItemCount(java.lang.Integer)
	 */
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#setCurrentPage(java.lang.Integer)
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#getCurrentPage()
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#getRange()
	 */
	public Integer getRange() {
		return range;
	}

	/* (non-Javadoc)
	 * @see com.trsvax.bootstrap.PaginationEnvironment#setRange(java.lang.Integer)
	 */
	public void setRange(Integer range) {
		this.range = range;
	}

}
