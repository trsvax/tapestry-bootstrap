package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.trsvax.bootstrap.services.TwitterBootstrapModule;

public class Pagination {
	
	@Parameter
	private Integer currentPage; 
	
	@Parameter(required=true)
	private Integer itemCount;
	
	@Parameter(value=TwitterBootstrapModule.PaginationRowsPerPage,required=true)
	private Integer rowsPerPage;
	
	@Parameter(value=TwitterBootstrapModule.PaginationRange,required=true)
	private Integer range;

	@Parameter(defaultPrefix="literal")
	private String event;
	
	@Parameter(defaultPrefix="literal")
	private String requestParameter;
	
	@Inject
	private ComponentResources resources;
		
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;
	
	private Integer pages;

	
	@SetupRender
	void setupRender() {
		if ( currentPage == null ) {
			currentPage = 1;
		}
		ActivationRequestParameter p = resources.getParameterAnnotation("currentPage", ActivationRequestParameter.class);
		if ( p != null && ! resources.isBound("requestParameter") ) {			
			requestParameter = p.value();
		}
		
		if ( requestParameter == null ) {
			event = resources.getId();
		}
	}
	
	@BeginRender
	void beginRender(MarkupWriter writer) {

		pages = itemCount/rowsPerPage;
		if ( itemCount % rowsPerPage != 0 ) {
			pages ++;
		}
		
		writer.element("div", "class","pagination");
		writer.element("ul");
		
		prev(writer);	
		int min = currentPage - range;
		int max = currentPage + range;
		if ( min < 0 ) {
			int offset = Math.abs(min) + 1;
			min += offset;
			max += offset;
		}
		if ( max > pages ) {
			max = pages;
		}
		for ( Integer i = min; i <= max; i++ ) {
			page(writer,i);
		}		
		next(writer);
		writer.end();
		writer.end();
	}
	
	
	private void page(MarkupWriter writer, Integer i) {
		if ( currentPage.equals(i)) {
			writer.element("li","class","active");
		} else {
			writer.element("li");
		}
		writer.element("a", "href",makeLink(i).toAbsoluteURI());
		writer.write(i.toString());
		writer.end();
		writer.end();
	}
	
	private void prev(MarkupWriter writer) {
		if ( currentPage == 1 ) {
			writer.element("li","class","prev disabled");
			writer.element("a", "href",makeLink(1));
		} else {
			writer.element("li","class","prev");
			writer.element("a", "href",makeLink( currentPage - 1));
		}
		writer.writeRaw("&larr; Previous");
		writer.end();
		writer.end();
	}
	
	private void next(MarkupWriter writer) {
		if ( currentPage >= pages ) {
			writer.element("li","class","next disabled");
			writer.element("a", "href","#");
		} else {
			writer.element("li","class","next");
			writer.element("a", "href",makeLink( currentPage + 1));
		}

		writer.writeRaw("Next &rarr;");
		writer.end();
		writer.end();
	}
	
	private Link makeLink(Integer count) {
		Link link = null;
		if ( event != null ) {
			link = resources.getContainerResources().createEventLink(event,count);
		} else {
			link = pageRenderLinkSource.createPageRenderLink(resources.getPageName());
			link.addParameter(requestParameter,count.toString());
		}
		return link;
	}

}
