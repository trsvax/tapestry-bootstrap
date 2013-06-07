package com.trsvax.bootstrap;

import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

public class IterableGridDateSource implements Iterable<Object>, GridDataSource {
	private final GridDataSource gridDataSource;
	private int index = 0;
	
	public IterableGridDateSource(GridDataSource gridDataSource) {
		this.gridDataSource = gridDataSource;
	}

	

	public Iterator<Object> iterator() {
		return new Iterator<Object>() {

			public boolean hasNext() {
				return index < gridDataSource.getAvailableRows();
			}

			public Object next() {
				return gridDataSource.getRowValue(index++);
			}

			public void remove() {
				throw new UnsupportedOperationException ("Cannot remove elements from GridDataSoruce");
			}
		};
	}



	public int getAvailableRows() {
		return gridDataSource.getAvailableRows();
	}



	public void prepare(int startIndex, int endIndex,
			List<SortConstraint> sortConstraints) {
		gridDataSource.prepare(startIndex, endIndex, sortConstraints);
	}



	public Object getRowValue(int index) {
		return gridDataSource.getRowValue(index);
	}



	public Class getRowType() {
		return gridDataSource.getRowType();
	}

}
