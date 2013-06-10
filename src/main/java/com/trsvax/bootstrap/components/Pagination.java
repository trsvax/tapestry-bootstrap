package com.trsvax.bootstrap.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
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
	
	@Parameter(value=PagerEnvironment.nextURL,required=true)
	private String nextURL;
	
	@Parameter(name="class",defaultPrefix="literal")
	@Property
	private String className;
	
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

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
		if ( className == null ) {
			className = "";
		}
		pageName = resources.getPageName();
		logger.info("page {} {}",pageName,availableRows);
		pages = new ArrayList<Pagination.Page>();
		if ( availableRows == null ) {
			return;
		}
		
		if ( currentPage > 1 ) {
			pages.add(new Page(currentPage-1,"&laquo;","prev"));
		}
		for ( Integer i = 1; i <= availableRows/rowsPerPage; i++ ) {
			pages.add(new Page(i,i.toString(), i == currentPage ? "active" : ""));
		}
		pages.add(new Page(currentPage+1,"&raquo;","next"));
		
		Link link = pageRenderLinkSource.createPageRenderLink(pageName);
		Page page = new Page(0,"","");
		for ( Entry<String,Object> entry : page.getPageParameters().entrySet()) {
				link.addParameterValue(entry.getKey(), entry.getValue());	
		}
		
		nextURL = link.toString().replace(id+ "=0", id+"=#index#");
	}
	
	public class Page {
		private final Map<String,Object> pageParameters;
		private final String pageLabel;
		private final Integer pageNum;
		private final String className;
		
		
		public Page(Integer pageNum, String pageLabel, String className) {
			this.pageParameters = new HashMap<String, Object>();
			for ( String name : request.getParameterNames() ) {
				this.pageParameters.put(name, request.getParameter(name));
			}
			this.pageParameters.put(id, pageNum.toString());
			this.pageLabel = pageLabel;
			this.pageNum = pageNum;
			this.className = className;
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
		
		public String getClassName() {
			return className;
		}
	}
	



}
