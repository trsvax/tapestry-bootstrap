package com.trsvax.bootstrap.environment;

public class PagerValues implements PagerEnvironment {
	
	private  Integer availableRows;
	private final int rowsPerPage;
	private final int currentPage;
	private final String id;
	private String nextURL;
	private String elementClass;
	
	public PagerValues(int rowPerPage, int currentPage, Integer availableRows, String id) {

		this.rowsPerPage = rowPerPage;
		this.currentPage = currentPage;
		this.availableRows = availableRows;
		this.id = id;

	}

	public void setNextURL(String url) {
		this.nextURL = url;
	}
	
	public String getNextURL() {
		return nextURL;
	}

	public void setAvailableRows(Integer availableRows) {
		this.availableRows = availableRows;
	}

	public Integer getAvailableRows() {
		return availableRows;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public String getID() {
		return id;
	}

	
}
