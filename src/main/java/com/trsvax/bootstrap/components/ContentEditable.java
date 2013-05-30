package com.trsvax.bootstrap.components;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


@SupportsInformalParameters
public class ContentEditable {
	
	@Parameter
	private String content;
	
	@Parameter
	private String uploadURL;
	
	@Parameter(required=true,defaultPrefix="literal")
	private String role;
	
	@Parameter(defaultPrefix="literal")
	private String event;
	
	@Parameter
	private Object[] context;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	private HttpServletRequest httpRequest;
	
	@Inject
	@Path("classpath:/com/trsvax/bootstrap/assets/components/ckeditor/ckeditor.js")
	private Asset ckediter;
	
	
	@BeginRender
	Object beginRender(MarkupWriter writer) {
		boolean enable = httpRequest.isUserInRole(role);;
		
		String clientID = javaScriptSupport.allocateClientId(resources);

		Element div = writer.element("div","id",clientID);
		if ( enable ) {
			Link link = resources.getContainerResources().createEventLink(event, context);
			div.attribute("data-url",link.toAbsoluteURI());
			div.attribute("contenteditable", "true");
		}
		resources.renderInformalParameters(writer);	
		
		if ( enable && uploadURL != null ) {
			javaScriptSupport.importJavaScriptLibrary(ckediter);
			javaScriptSupport.addScript("CKEDITOR.inline( '%s', { " +
					"filebrowserUploadUrl: '%s'," +
					"filebrowserBrowseUrl: '/studio/browse/view', " +
					//"coreStyles_bold	: { element : 'span', attributes : {'class': 'Bold'} }," +
					//"coreStyles_italic : { element : 'span', attributes : {'class': 'Italic'} },"+
					"on: {" +
					"  instanceReady: function(ev) {" +
					//"     this.dataProcessor.writer.selfCloseingEnd = ' />';" +
					"   }" +
					"}" +
					"});", clientID, uploadURL);
		}
		
		if ( content != null && content.equals("hide") ) {
			return false;			
		}
		if ( content != null ) {
			writer.writeRaw(content);
			return false;
		}

		return true;
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
