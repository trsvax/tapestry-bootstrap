package com.trsvax.bootstrap.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Mixin;

import com.trsvax.bootstrap.mixins.Pager;

public class Thumbnails {
	
	@Mixin
	private Pager pager;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		//writer.element("ul", "class","thumbnails");
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		//writer.end();
	}

}
