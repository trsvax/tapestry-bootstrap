package com.trsvax.bootstrap.environment;

public interface PagerEnvironment {
	
	public final static String availableRows = "env:com.trsvax.bootstrap.environment.PagerEnvironment.availableRows";
	public final static String rowsPerPage = "env:com.trsvax.bootstrap.environment.PagerEnvironment.rowsPerPage";
	public final static String currentPage = "env:com.trsvax.bootstrap.environment.PagerEnvironment.currentPage";
	public final static String id = "env:com.trsvax.bootstrap.environment.PagerEnvironment.ID";
	
	public Integer getAvailableRows();
	public void setAvailableRows(Integer AvailableRows);
	public int getRowsPerPage();
	public int getCurrentPage();
	public String getID();
	
	

}
