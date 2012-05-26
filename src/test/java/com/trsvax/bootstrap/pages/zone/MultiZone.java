package com.trsvax.bootstrap.pages.zone;

import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class MultiZone {
	@Property
	  @Persist
	  private int clickCount;
	
	@Property
	  @Persist
	  private int clickCount2;

	  @InjectComponent
	  private org.apache.tapestry5.corelib.components.Zone counterZone;
	  
	  @InjectComponent
	  private org.apache.tapestry5.corelib.components.Zone counterZone2;

	  Object onActionFromClicker()
	  {
	    clickCount++;
	    clickCount2 += 2;

	    return new MultiZoneUpdate("counterZone", counterZone).add("counterZone2", counterZone2);
	  }

}
