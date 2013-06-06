package com.trsvax.bootstrap.services;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PageLink;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;
import org.slf4j.Logger;

import com.trsvax.bootstrap.mixins.BootstrapForm;
import com.trsvax.bootstrap.mixins.Tooltip;


public class DefaultMixinWorker implements ComponentClassTransformWorker2 {
	//private final List<Class<?>> configuration;
	private final Logger logger;
	
	
	public DefaultMixinWorker( Logger logger) {
		//this.configuration = configuration;
		this.logger = logger;
	}

	public void transform(PlasticClass plasticClass, TransformationSupport support, MutableComponentModel model) {
		logger.info("name {}",model.getComponentClassName());
		if ( PageLink.class.getName().equals(model.getComponentClassName())) {
			model.addMixinClassName(Tooltip.class.getName());
			logger.info("added tooltip");
		}
		
		if ( Form.class.getName().equals(model.getComponentClassName())) {
			model.addMixinClassName(BootstrapForm.class.getName());
		}
		
	}

}
