package com.trsvax.bootstrap.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class ButtonTest  extends SeleniumTestCase {
	
	@Test
    public void testButtons()
    {
		open("/button");
		
		//assertAttribute("//a[@id='default']@class","btn");
		//assertAttribute("//button[@id='defaultButton']@class","btn");
		
        
    }

}
