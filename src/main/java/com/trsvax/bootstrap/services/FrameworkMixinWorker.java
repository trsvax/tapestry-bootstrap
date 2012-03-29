package com.trsvax.bootstrap.services;

import java.util.List;

import org.apache.tapestry5.corelib.components.Loop;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;
import org.slf4j.Logger;

import com.trsvax.bootstrap.mixins.FW;
import com.trsvax.bootstrap.mixins.LoopEnvironmentMixin;

/**
 * @since 5.2.6
 */
public class FrameworkMixinWorker implements ComponentClassTransformWorker2 {
	
	private final Logger logger;
	
	public FrameworkMixinWorker(Logger logger) {
		this.logger = logger;
	}
	

	public void transform(PlasticClass plasticClass, TransformationSupport support, MutableComponentModel model) {
		if ( exclude(model)) {
			return;
		}
		logger.info("check {}",model.getComponentClassName());
	    if ( ! hasFW(model.getMixinClassNames())) {
	    	
			if ( model.getSupportsInformalParameters() ) {
				//logger.info("add sip {}", model.getComponentClassName());
				//model.addMixinClassName(AW.class.getName(),"before:*");
			}
			model.addMixinClassName(FW.class.getName());
	    }
	    
	    if ( model.getComponentClassName().equals(Loop.class.getName())) {
	    	logger.info("add mixin {} to {}",LoopEnvironmentMixin.class.getName(),Loop.class);
	    	model.addMixinClassName(LoopEnvironmentMixin.class.getName());
	    }
		
	}
	
	boolean hasFW(List<String> mixins) {
		if ( mixins == null ) {
			return false;
		}
		String fw = FW.class.getName();
		for ( String name : mixins ) {
			if ( fw.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	boolean exclude(MutableComponentModel model) {
		if ( model.isPage() ) {
			return true;
		}
		String name = model.getComponentClassName();
		// might be able to fool this but I'm not sure that matters
		if ( name.contains("mixins")) {
			return true;
		}
		if ( name.equals("com.trsvax.bootstrap.mixins.FW")) {
			return true;
		}
		if ( name.equals("com.trsvax.bootstrap.mixins.AW")) {
			return true;
		}
		return false;
	}
	
}
