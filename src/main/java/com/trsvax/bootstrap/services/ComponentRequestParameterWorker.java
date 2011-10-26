package com.trsvax.bootstrap.services;

import java.util.List;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.internal.services.ComponentClassCache;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.FieldHandle;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticField;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.TransformConstants;
import org.apache.tapestry5.services.ValueEncoderSource;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

public class ComponentRequestParameterWorker implements ComponentClassTransformWorker2 {
	private final Request request;
	private final ValueEncoderSource valueEncoderSource;
	private final ComponentClassCache classCache;
	
	public ComponentRequestParameterWorker(Request request, ValueEncoderSource valueEncoderSource, 
			ComponentClassCache componentClassCache) {
		this.request = request;
		this.valueEncoderSource = valueEncoderSource;
		this.classCache = componentClassCache;
	}

	public void transform(PlasticClass plasticClass, TransformationSupport support,
			MutableComponentModel model) {
		if ( model.isPage() ) {
			return;
		}
		
		List<PlasticField> fields = plasticClass.getFieldsWithAnnotation(ActivationRequestParameter.class);
		
		if (fields.isEmpty())
			return;
		
		PlasticMethod setupRender = plasticClass.introduceMethod(TransformConstants.SETUP_RENDER_DESCRIPTION);
		
		for ( PlasticField field : fields ) {
			ActivationRequestParameter annotation = field.getAnnotation(ActivationRequestParameter.class);
			String parameterName = getParameterName(field, annotation);
			Class<?> fieldType = classCache.forName(field.getTypeName());
			ValueEncoder<?> encoder = valueEncoderSource.getValueEncoder(fieldType);
			FieldHandle handle = field.getHandle();
			String fieldName = String.format("%s.%s", field.getPlasticClass().getClassName(), field.getName());
			
			setValue(setupRender, field, fieldName, handle, parameterName, encoder);
		}
		
		model.addRenderPhase(SetupRender.class);		
	}


	void setValue(PlasticMethod method, final PlasticField field, String fieldName, final FieldHandle handle,
			final String parameterName, final ValueEncoder<?> encoder) {
		
		method.addAdvice(new MethodAdvice() {

			public void advise(MethodInvocation invocation) {
				 
				String clientValue = request.getParameter(parameterName);				 				 
				if ( clientValue != null  ) {
					 Object value = encoder.toValue(clientValue);
					 handle.set(invocation.getInstance(), value);
				}			
				
	            invocation.proceed();
			}			
		});
		
	}
	
	
	// I don't think this works
	private String getParameterName(PlasticField field, ActivationRequestParameter annotation) {
        if (annotation.value().equals(""))
            return field.getName();

        return annotation.value();
	}
	

}
