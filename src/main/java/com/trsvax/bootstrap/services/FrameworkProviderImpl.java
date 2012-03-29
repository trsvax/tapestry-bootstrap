package com.trsvax.bootstrap.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.annotations.Primary;

import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkProvider;

public class FrameworkProviderImpl implements FrameworkProvider {
	private final BootstrapProvider bootstrapProvider;
	
	
	
	public FrameworkProviderImpl(@Primary BootstrapProvider bootstrapProvider) {
		this.bootstrapProvider = bootstrapProvider;
	}
	
	public boolean setupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( ! instrument(mixin) ) {
			return false;
		}
		String componentName = mixin.getComponentResources().getContainer().getClass().getCanonicalName();
		String fw = mixin.getFW();

		mixin.setRoot(writer.elementNS(fw, fw+".mixin"));
		writer.attributes(fw+".type", mixin.getType());
		writer.attributes(fw+".component", componentName );
		return bootstrapProvider.setupRender(mixin, writer);
	}

	public boolean beginRender(FrameworkMixin mixin, MarkupWriter writer) {
		return bootstrapProvider.beginRender(mixin, writer);
	}

	public boolean afterRender(FrameworkMixin mixin, MarkupWriter writer) {
		return bootstrapProvider.afterRender(mixin, writer);
	}

	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer) {
		if ( mixin.getRoot() != null ) {
			writer.end();
		}
		return bootstrapProvider.cleanupRender(mixin, writer);
	}
	
	public boolean renderMarkup(MarkupWriter writer) {
		return bootstrapProvider.renderMarkup(writer);

	}

	public boolean instrument(FrameworkMixin mixin) {
		return bootstrapProvider.instrument(mixin);
	}

}
