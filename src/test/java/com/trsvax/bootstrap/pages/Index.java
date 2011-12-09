package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.environment.ButtonSize;
import com.trsvax.bootstrap.environment.ButtonType;
import com.trsvax.bootstrap.environment.ButtonValues;
import com.trsvax.bootstrap.environment.LabelType;
import com.trsvax.bootstrap.environment.LabelValues;

public class Index {
	
    @Inject
    private AlertManager alertManager;
	
	@BeginRender
	void beginRender() {
		alertManager.info("info");
		alertManager.error("error");
	}
	
	public Object[] getValues() {
			Object[] values = {getButtonValues(),getLabelValues()};
			return values;
	}
	
    public ButtonValues getButtonValues() {
    	ButtonValues values = new ButtonValues(null);
    	values.setButtonType(ButtonType.danger);
    	values.setButtonSize(ButtonSize.small);
    	return values;
    }
    
    public LabelValues getLabelValues() {
    	LabelValues values = new LabelValues(null);
    	values.setLabelType(LabelType.warning);
    	return values;
    }

}
