package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.bootstrap.environment.ButtonSize;
import com.trsvax.bootstrap.environment.ButtonType;
import com.trsvax.bootstrap.environment.ButtonValues;

public class Index {
	
    @Inject
    private AlertManager alertManager;
	
	@BeginRender
	void beginRender() {
		alertManager.info("info");
		alertManager.error("error");
	}
	
    public ButtonValues getButtonValues() {
    	ButtonValues values = new ButtonValues();
    	values.setType(ButtonType.danger);
    	values.setSize(ButtonSize.small);
    	return values;
    }

}
