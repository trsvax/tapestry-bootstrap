package com.trsvax.bootstrap.mixins;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ValueEncoderSource;

import com.trsvax.bootstrap.LoopEnvironment;
import com.trsvax.bootstrap.LoopValues;
import com.trsvax.bootstrap.PaginationEnvironment;
import com.trsvax.bootstrap.PaginationValues;

public class Pager<T> {
	
		@Parameter(required=true)
		private List<T> source;
		
		@Parameter
		private String event;
		
		@Parameter(value="resources.container.componentResources.id")
		private String parameterName;
		
		@Parameter
		private Integer currentPage;
		
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
	    	paginationValues.setItemCount(source.size());
						
			LoopValues<T> loopValues = new LoopValues<T>();
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
		
		PaginationValues values(final String event,final String parameterName) {
			return new PaginationValues() {
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
