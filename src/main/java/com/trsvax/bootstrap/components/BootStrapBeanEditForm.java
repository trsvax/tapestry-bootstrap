package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;


@SupportsInformalParameters
@Import(library="classpath:/com/trsvax/bootstrap/validation.js")
public class BootStrapBeanEditForm extends BeanEditForm {
	
	@Inject
	private ComponentResources resources;
	

	
	public String getFormType() {
		String formtype = resources.getInformalParameter("class", String.class);
		if ( formtype != null ) {
			return formtype;
		}
		return " ";
	}
	

}
