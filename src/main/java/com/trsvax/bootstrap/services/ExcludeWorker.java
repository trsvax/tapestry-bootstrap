package com.trsvax.bootstrap.services;

import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.TransformConstants;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;
import org.slf4j.Logger;

import com.trsvax.bootstrap.annotations.Exclude;
import com.trsvax.bootstrap.environment.FrameWorkEnvironment;

public class ExcludeWorker implements ComponentClassTransformWorker2 {
	private final Environment environment;
	private final Logger logger;
	
	public ExcludeWorker(Environment environment, Logger logger) {
		this.environment = environment;
		this.logger = logger;
	}

	public void transform(PlasticClass plasticClass, TransformationSupport support, MutableComponentModel model) {
		Exclude exclude =  plasticClass.getAnnotation(Exclude.class);
		if ( exclude != null ) {
			PlasticMethod setupRender = plasticClass.introduceMethod(TransformConstants.SETUP_RENDER_DESCRIPTION);
			excluder(setupRender,exclude);
			model.addRenderPhase(SetupRender.class);
		}
		
	}

	
	private void excluder(final PlasticMethod setupRender, final Exclude exclude) {
		setupRender.addAdvice(new MethodAdvice() {

			public void advise(MethodInvocation invocation) {
				 FrameWorkEnvironment excludeEnvironment = environment.peek(FrameWorkEnvironment.class);
					if ( excludeEnvironment != null ) {
						for ( String pattern : exclude.stylesheet() ) {
							excludeEnvironment.addExclude(pattern);
						}
					}
				 invocation.proceed();
			}
			
		});
		
	}


}
