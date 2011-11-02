package com.trsvax.bootstrap.pages.mediagrid;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

public class MediaGridIndex {
	
	@Property
	private List<Integer> media;
	
	
	@Property
	private Integer index;
	
	@Property
	private Integer value;
	
	@SetupRender
	void setupRender() {
		media = new ArrayList<Integer>();
		for ( int i = 0; i < 10; i++ ) {
			media.add(i);
		}
	}

}
