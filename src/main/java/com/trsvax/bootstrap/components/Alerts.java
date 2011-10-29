package com.trsvax.bootstrap.components;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.alerts.Alert;
import org.apache.tapestry5.alerts.AlertStorage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import com.trsvax.bootstrap.environment.AlertType;

public class Alerts {
	
	@Property
	private Alert alert;
	
	@SessionState(create = false)
	private AlertStorage storage;
    
    public List<Alert> getAlerts() {
    	if ( storage == null ) {
    		return Collections.emptyList();
    	}
    	return storage.getAlerts();
    }
    
    public String getMessage() {
    	return alert.message;
    }
    
    public AlertType getType() {
    	switch (alert.severity) {
    		case INFO:
    			return AlertType.info;
    		case WARN:
    			return AlertType.warning;
    		case ERROR:
    			return AlertType.error;
    	}
    	return null;
    }

}
