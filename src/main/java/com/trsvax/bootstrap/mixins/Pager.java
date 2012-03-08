package com.trsvax.bootstrap.mixins;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ValueEncoderSource;

import com.trsvax.bootstrap.environment.LoopEnvironment;
import com.trsvax.bootstrap.environment.LoopValues;
import com.trsvax.bootstrap.environment.PaginationEnvironment;
import com.trsvax.bootstrap.environment.PaginationValues;

public class Pager<T> {
	
		@Parameter(required=true,allowNull=false)
		private List<T> source;
		
		@Parameter
		private String event;
		
		@Parameter(value="resources.container.componentResources.id")
		private String parameterName;
		
		@Parameter
		private Integer currentPage;
		
		@Parameter(defaultPrefix="literal")
		private Integer rowsPerPage;
		
	 	@Inject
	  	private Environment environment;
	
		@Inject
		private Request request;
		
		@Inject
		private ValueEncoderSource valueEncoderSource;
		
		@Inject
		@Property
		private ComponentResources resources;
			
		@Inject
		private PageRenderLinkSource pageRenderLinkSource;
		

		@SetupRender
		void setupRender()  {			
			PaginationValues paginationValues = values(event,parameterName);
	    	paginationValues.setCurrentPage(currentPage());
	    	if ( rowsPerPage != null ) {
	    		paginationValues.setRowsPerPage(rowsPerPage);
	    	}
	    	paginationValues.setItemCount(source.size());
			@SuppressWarnings("unchecked")
			LoopValues<T> loopValues = new LoopValues<T>(environment.peek(LoopEnvironment.class));
			loopValues.setSource(source(paginationValues));
    	
			environment.push(LoopEnvironment.class, loopValues);
			environment.push(PaginationEnvironment.class, paginationValues);
		}
		
		@CleanupRender
		void cleanupRender() {
			environment.pop(LoopEnvironment.class);
			environment.pop(PaginationEnvironment.class);
		}
		
		List<T> source(PaginationValues paginationValues) {
			int fromIndex = (paginationValues.getCurrentPage() - 1) * paginationValues.getRowsPerPage();
			int toIndex = fromIndex + paginationValues.getRowsPerPage();
			if ( toIndex > paginationValues.getItemCount()) {
				toIndex = paginationValues.getItemCount();
			}
			if ( fromIndex < 0 || fromIndex > toIndex || toIndex > source.size() ) {
				return Collections.emptyList();
			}
			
			return source.subList(fromIndex, toIndex);
		}
		
		Integer currentPage() {
			if ( currentPage != null ) {
				return currentPage;
			}
			if ( request.getParameter(parameterName) == null ) {
				return 1;
			}
			return valueEncoderSource.getValueEncoder(Integer.class).toValue(request.getParameter(parameterName));
		}
		
		PaginationValues values( final String event,final String parameterName) {
			return new PaginationValues(environment.peek(PaginationEnvironment.class)) {
	    		public Link getLink(Integer count) {
	    			Link link = null;
	    			if ( event != null ) {
	    				link = resources.getContainerResources().createEventLink(event,count);
	    			} else {
	    				link = pageRenderLinkSource.createPageRenderLink(resources.getPageName());
	    				link.addParameter(parameterName,count.toString());
	    			}
	    			return link;
	    		};
			};
		}
}
