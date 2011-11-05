package com.trsvax.bootstrap.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.ComputedValue;
import org.apache.tapestry5.plastic.FieldConduit;
import org.apache.tapestry5.plastic.InstanceContext;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticField;
import org.apache.tapestry5.services.BindingSource;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

import com.trsvax.bootstrap.annotations.Connect;


public class ConnectWorker implements ComponentClassTransformWorker2 {
	private final BindingSource bindingSource;
	
	
	public ConnectWorker(BindingSource bindingSource) {
		this.bindingSource = bindingSource;
	}

	
	public void transform(PlasticClass plasticClass,
			TransformationSupport support, MutableComponentModel model) {
				
		for ( PlasticField field : plasticClass.getFieldsWithAnnotation(Connect.class) ) {
			transformField(field);
		}
	}
	
	private void transformField(PlasticField field) {
		Connect annotation = field.getAnnotation(Connect.class);		
        field.claim(annotation);		
        ComputedValue<FieldConduit<Object>> provider = createFieldValueConduitProvider(field,annotation.value());	
        field.setComputedConduit(provider);
    }


	private ComputedValue<FieldConduit<Object>> createFieldValueConduitProvider(
			PlasticField field, final String expression) {

		return new ComputedValue<FieldConduit<Object>>() {
			public FieldConduit<Object> get(InstanceContext context) {
				return  new FieldConduit<Object>() {
					
					public void set(Object instance, InstanceContext context, Object value) {
						ComponentResources resources = context.get(ComponentResources.class);
						Binding binding = bindingSource.newBinding("default", resources, "prop", expression);
						binding.set(value);
					}
					
					public Object get(Object instance, InstanceContext context) {
						ComponentResources resources = context.get(ComponentResources.class);
						Binding binding = bindingSource.newBinding("default", resources, "prop", expression);
						return binding.get();
					}
				};
			}
		};
	}
	
	
}
