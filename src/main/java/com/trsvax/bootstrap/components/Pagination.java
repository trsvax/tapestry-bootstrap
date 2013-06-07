package com.trsvax.bootstrap.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.PagerEnvironment;

/**
 * @tapestrydoc
 */
public class Pagination  {
	
	//@Property
	//@Environmental(false)
	//private PagerEnvironment pagination;
	
	@Parameter(value=PagerEnvironment.currentPage,required=true)
	@Property
	private Integer currentPage; 
	
	@Parameter(value=PagerEnvironment.availableRows,required=true)
	private Integer availableRows;
	
	@Parameter(value=PagerEnvironment.rowsPerPage,required=true)
	private Integer rowsPerPage;
	
	@Parameter(value=PagerEnvironment.id,required=true)
	private String id;

	@Property
	private List<Page> pages;
	@Property
	private Page page;
	
	@Property
	private String pageName;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private Request request;
	
	@Inject
	private Logger logger;
			
	@BeginRender
	void beginRender(MarkupWriter writer) {
		pageName = resources.getPageName();
		logger.info("page {} {}",pageName,availableRows);
		pages = new ArrayList<Pagination.Page>();
		if ( availableRows == null ) {
			return;
		}
		
		pages.add(new Page(currentPage-1,"&laquo;"));
		for ( Integer i = 1; i <= availableRows/rowsPerPage; i++ ) {
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
		private final Map<String,Object> pageParameters;
		private final String pageLabel;
		private final Integer pageNum;
		
		
		public Page(Integer pageNum, String pageLabel) {
			this.pageParameters = new HashMap<String, Object>();
			for ( String name : request.getParameterNames() ) {
				this.pageParameters.put(name, request.getParameter(name));
			}
			this.pageParameters.put(id, pageNum.toString());
			this.pageLabel = pageLabel;
			this.pageNum = pageNum;
		}
		
		public Integer getPageNum() {
			return pageNum;
		}
		
		public Map<String,Object> getPageParameters() {
			return pageParameters;
		}
		
		public String getPageLabel() {
			return pageLabel;
		}
	}
	



}
