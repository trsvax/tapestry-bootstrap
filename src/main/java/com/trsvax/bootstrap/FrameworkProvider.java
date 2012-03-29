package com.trsvax.bootstrap;

import org.apache.tapestry5.MarkupWriter;

public interface FrameworkProvider {
	
	public boolean instrument(FrameworkMixin mixin);
	
	public boolean setupRender(FrameworkMixin mixin, MarkupWriter writer);
	public boolean beginRender(FrameworkMixin mixin, MarkupWriter writer);
	public boolean afterRender(FrameworkMixin mixin, MarkupWriter writer);
	public boolean cleanupRender(FrameworkMixin mixin, MarkupWriter writer);
	public boolean renderMarkup(MarkupWriter writer);

}
