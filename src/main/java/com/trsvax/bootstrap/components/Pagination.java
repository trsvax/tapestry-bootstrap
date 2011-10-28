package com.trsvax.bootstrap.components;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.trsvax.bootstrap.PaginationEnvironment;

public class Pagination {
	
	@Property
	@Environmental
	private PaginationEnvironment values;
	
	@Parameter(value="values.currentPage",required=true)
	private Integer currentPage; 
	
	@Parameter(value="values.itemCount",required=true)
	private Integer itemCount;
	
	@Parameter(value="values.rowsPerPage",required=true)
	private Integer rowsPerPage;
	
	@Parameter(value="values.range",required=true)
	private Integer range;
			
	@BeginRender
	void beginRender(MarkupWriter writer) {

		Integer pageCount = itemCount/rowsPerPage;
		if ( itemCount % rowsPerPage != 0 ) {
			pageCount ++;
		}
		int min = currentPage - range;
		int max = currentPage + range;
		if ( min < 0 ) {
			int offset = Math.abs(min) + 1;
			min += offset;
			max += offset;
		}
		if ( max > pageCount ) {
			max = pageCount;
		}
		
		writer.element("div", "class","pagination");
		writer.element("ul");		
		prev(writer);
		for ( Integer page = min; page <= max; page++ ) {
			link(writer,page);
		}		
		next(writer,pageCount);
		writer.end();
		writer.end();
	}
	
	
	private void link(MarkupWriter writer, Integer page) {
		if ( currentPage.equals(page)) {
			writer.element("li","class","active");
		} else {
			writer.element("li");
		}
		writer.element("a", "href",makeLink(page));
		writer.write(page.toString());
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
	
	private void next(MarkupWriter writer, Integer pageCount) {
		if ( currentPage >= pageCount ) {
			writer.element("li","class","next disabled");
			writer.element("a", "href","#");
		} else {
			writer.element("li","class","next");
			writer.element("a", "href",makeLink( currentPage + 1),"id","next");
		}

		writer.writeRaw("Next &rarr;");
		writer.end();
		writer.end();
	}
	
	private Link makeLink(Integer count) {
		return values.getLink(count);		
	}

}
