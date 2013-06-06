package com.trsvax.bootstrap.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.environment.PaginationEnvironment;

/**
 * @tapestrydoc
 */
public class Pagination  {
	
	@Property
	@Environmental(false)
	private PaginationEnvironment pagination;
	
	@Parameter(value=PaginationEnvironment.currentPage,required=true)
	@Property
	private Integer currentPage; 
	
	@Parameter(value=PaginationEnvironment.itemCount,required=true)
	private Integer itemCount;
	
	@Parameter(value=PaginationEnvironment.rowsPerPage,required=true)
	private Integer rowsPerPage;
	
	@Parameter(value=PaginationEnvironment.range,required=true)
	private Integer range;
	
	@Property
	private List<Page> pages;
	@Property
	private Page page;
	
	@Property
	private String pageName;
	
	@Inject
	private ComponentResources resources;
			
	@BeginRender
	void beginRender(MarkupWriter writer) {
		pageName = resources.getPageName();
		pages = new ArrayList<Pagination.Page>();
		
		pages.add(new Page(currentPage-1,"&laquo;"));
		for ( Integer i = 1; i <= itemCount/rowsPerPage; i++ ) {
			pages.add(new Page(i,i.toString()));
		}
		pages.add(new Page(currentPage-1,"&raquo;"));
	}
	
	public String getClassName() {
		if ( page.getPageNum().equals(currentPage)) {
			return "active";
		}
		return "";
	}
	

	
	public class Page {
		private final Map<String,Integer> pageParameters;
		private final String pageLabel;
		private final Integer pageNum;
		
		
		public Page(Integer pageNum, String pageLabel) {
			this.pageParameters = new HashMap<String, Integer>();
			this.pageParameters.put("page", pageNum);
			this.pageLabel = pageLabel;
			this.pageNum = pageNum;
		}
		
		public Integer getPageNum() {
			return pageNum;
		}
		
		public Map<String,Integer> getPageParameters() {
			return pageParameters;
		}
		
		public String getPageLabel() {
			return pageLabel;
		}
	}
	



}
