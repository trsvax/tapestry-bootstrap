package com.trsvax.bootstrap;

import org.apache.tapestry5.beaneditor.Validate;

public class Address {
	public Honorific honorific;

	@Validate("required")
	  public String firstName;

	@Validate("required")
	  public String lastName;

	  public String street1;

	  public String street2;

	  public String city;

	  public String state;

	  @Validate("required,regexp")
	  public String zip;

	  public String email;

	  public String phone;

}
