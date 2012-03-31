package com.trsvax.bootstrap.services.bootstrapprovider;

import org.apache.tapestry5.MarkupWriter;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;

public class LayoutProvider extends AbstractFrameworkProvider implements BootstrapProvider {
	

	public boolean instrument(FrameworkMixin mixin) {
		return false;
	}

	

}
