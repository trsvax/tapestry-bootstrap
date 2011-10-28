package com.trsvax.bootstrap;

import java.util.List;

public class LoopValues<T> implements LoopEnvironment<T> {
	private List<T> source;
	private List<T> view;
	

	public List<T> getSource() {
		return source;
	}

	public void setSource(List<T> source) {
		this.source = source;
		setView(0, source.size());
	}

	
	public List<T> getView() {
		return view;
	}
	
	public void setView(Integer fromIndex, Integer toIndex) {
		this.view = source.subList(fromIndex, toIndex);
	}

	
}
