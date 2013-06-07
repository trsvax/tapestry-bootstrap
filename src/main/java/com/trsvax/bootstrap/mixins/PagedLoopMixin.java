package com.trsvax.bootstrap.mixins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.BindParameter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.trsvax.bootstrap.environment.PagerEnvironment;

public class PagedLoopMixin {
	
	@BindParameter
	private Iterable source;

 	@Environmental(false)
  	private PagerEnvironment env;
 	
 	@Inject
 	private ComponentResources resources;
 	
 	@Inject
 	private Logger logger;
 	
 	private int rowsPerPage;
 	private int currentPage;
 	private int availableRows;
 	
 	private int startIndex;
 	private int endIndex;
	
	@SetupRender
	void setupRender() {
		String id = resources.getContainerResources().getId();
		if ( id == null || env == null) {
			return;
		}

		if ( ! id.equals(env.getID())) {
			return;
		}
		rowsPerPage = env.getRowsPerPage();
		currentPage = env.getCurrentPage();

		
		Class<?> type = resources.getContainerResources().getBoundType("source");
		logger.info("source type {}",type);
		if ( Collection.class.isAssignableFrom(type)) {
			@SuppressWarnings("unchecked")
			Collection<Object> c = (Collection<Object>) source;
			availableRows = c.size();
			env.setAvailableRows(availableRows);
			calculateIndexes();
			source = source(c);
			return;
		}
		if ( GridDataSource.class.isAssignableFrom(type)) {
			GridDataSource ds = (GridDataSource) source;
			availableRows = ds.getAvailableRows();
			env.setAvailableRows(availableRows);
			calculateIndexes();
			source = source(ds);
			return;
		}
		throw new RuntimeException("Loop Paging not supported by " + type);
	}
	
	


	private void calculateIndexes() {
		startIndex = (currentPage - 1) * rowsPerPage;
		endIndex = startIndex + rowsPerPage;
		if ( endIndex > availableRows) {
			endIndex = availableRows;
		}

		//logger.info("start {} end {}",startIndex,endIndex);
	}
	
	private List<Object> source(Collection<Object> source) {
		List<Object> subSource = new ArrayList<Object>();
		int index = 0;
		for ( Object o : source ) {
			if ( index >= startIndex && index < endIndex ) {
				subSource.add(o);
			}
			index++;
		}
		
		return subSource;
	}
		
		
		private List<Object> source(GridDataSource source) {
			if ( startIndex < 0 || startIndex > endIndex || endIndex > availableRows ) {
				return Collections.emptyList();
			}
			List<SortConstraint> sortConstraints = new ArrayList<SortConstraint>();

			source.prepare(startIndex, endIndex, sortConstraints);
			List<Object> values = new ArrayList<Object>();
			for ( int i = startIndex; i < endIndex; i++ ) {
				values.add(source.getRowValue(i));
			}
			return values;
	}

}
