package com.trsvax.bootstrap.pages.zone;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Zone {
	@Property
	  @Persist
	  private int clickCount;

	  @InjectComponent
	  private org.apache.tapestry5.corelib.components.Zone counterZone;

	  Object onActionFromClicker()
	  {
	    clickCount++;

	    return counterZone.getBody();
	  }
}
