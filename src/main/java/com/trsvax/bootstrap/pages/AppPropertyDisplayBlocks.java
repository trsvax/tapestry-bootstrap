package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.services.PropertyOutputContext;

public class AppPropertyDisplayBlocks {

	@Environmental
	private PropertyOutputContext context;

	public String getConvertedImageValue()
	{
		String value = (String) context.getPropertyValue();

		if (value == null) return null;
		
		if ( value.startsWith("http")) {
			return value;
		}

		return "https://s3.amazonaws.com/assets.judypaul.com/" + value;
	}
	
	public String getConvertedMoneyValue()
	{
		Object value = context.getPropertyValue();

		if (value == null) return null;

		return value.toString();
	}

}
