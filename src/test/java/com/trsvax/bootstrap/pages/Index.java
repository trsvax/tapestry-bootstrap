package com.trsvax.bootstrap.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Index {
	
    @Inject
    private AlertManager alertManager;
	
	@BeginRender
	void beginRender() {
		alertManager.info("info");
		alertManager.error("error");
	}

}
