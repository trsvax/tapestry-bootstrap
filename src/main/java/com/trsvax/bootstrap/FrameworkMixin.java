package com.trsvax.bootstrap;

import java.util.Map;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.Messages;

public interface FrameworkMixin {

	public String getFW();
	public String getType();
	public Map<String,Object> getParms();
	public Element getRoot();
	public void setRoot(Element element);
	public ComponentResources getComponentResources();
	public String getComponentClassName();
	public Messages getMessages();

}
