package com.trsvax.bootstrap.pages.mediagrid;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

public class MediaGridIndex {
	@ActivationRequestParameter
	private Integer count;
	
	@Property
	private List<Integer> media;
		
	@Property
	private Integer index;
	
	@Property
	private Integer value;
	
	@SetupRender
	void setupRender() {
		media = new ArrayList<Integer>();
		if ( count == null ) {
			count = 10;
		}
		for ( int i = 0; i < count; i++ ) {
			media.add(i);
		}
	}

}
