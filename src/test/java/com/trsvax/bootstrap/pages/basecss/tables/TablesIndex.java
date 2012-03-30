package com.trsvax.bootstrap.pages.basecss.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

import com.trsvax.bootstrap.Address;

public class TablesIndex {
	
	@Property
	private List<Address> addresses;
	
	@SetupRender
	void setupRender() {
		addresses = new ArrayList<Address>();
		Address address = new Address();
		address.firstName = "barry";
		for ( int i = 0; i < 20; i++) {
			addresses.add(address);
		}
	}

}
