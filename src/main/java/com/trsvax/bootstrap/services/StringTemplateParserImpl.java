package com.trsvax.bootstrap.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;

import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.services.dynamic.DynamicTemplate;
import org.apache.tapestry5.services.dynamic.DynamicTemplateParser;

public class StringTemplateParserImpl implements StringTemplateParser {
	private final DynamicTemplateParser parser;
	
	public StringTemplateParserImpl(DynamicTemplateParser parser) {
		this.parser = parser;
	}

	public DynamicTemplate parse(String template) {
		return parser.parseTemplate(stringResource(template));
	}
	
	 private Resource stringResource(final String template) {
			return new Resource() {
				
						
				public Resource withExtension(String arg0) {
					return null;
				}
				
				public URL toURL() {
					return null;
				}
				
				public InputStream openStream() throws IOException {
					return new ByteArrayInputStream(template.getBytes());
				}
				
				public String getPath() {
					return null;
				}
				
				public String getFolder() {
					return null;
				}
				
				public String getFile() {
					return null;
				}
				
				public Resource forLocale(Locale arg0) {
					return null;
				}
				
				public Resource forFile(String arg0) {
					return null;
				}
				
				public boolean exists() {
					return true;
				}

				@Override
				public boolean equals(Object obj) {
					return template.equals(obj);
				}

				@Override
				public int hashCode() {
					return template.hashCode();
				}

				public boolean isVirtual() {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}

}
