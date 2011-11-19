package com.trsvax.bootstrap.pages.beangrid;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

import com.trsvax.bootstrap.Address;
import com.trsvax.bootstrap.Honorific;

public class BeanGridIndex {
	
	@PageActivationContext
	@Property
	private String formtype;
	
	@Property
	private List<Address> addresses;
	
	@SetupRender
	private void setupRender() {
		addresses = new ArrayList<Address>();
		for ( Integer i = 0; i < 10; i++ ) {
			for ( String d : data ) {
				Address address = new Address();
				address.firstName = d;
				address.honorific = Honorific.DR;
				address.zip = i.toString();
				addresses.add(address);
			}
		}
	}
	
	private final String[] data = {"Barry","Judy"};

}
