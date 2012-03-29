package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;

import com.trsvax.bootstrap.AbstractFrameworkProvider;
import com.trsvax.bootstrap.BootstrapProvider;
import com.trsvax.bootstrap.FrameworkMixin;

public class DefaultProvider extends AbstractFrameworkProvider implements BootstrapProvider {

	@Override
	public boolean renderMarkup(MarkupWriter writer) {
		final Set<Element> pop = new HashSet<Element>();
		Element root = writer.getDocument().getRootElement();
		if ( root == null ) {
			return false;
		}
		root.visit(new Visitor() {
			
			public void visit(Element element) {
				if ( "tb".equals(element.getNamespace())) {
					pop.add(element);
				}
				
			}
		});
		for ( Element element : pop ) {
			element.pop();
		}
		return false;
	}

	public boolean instrument(FrameworkMixin mixin) {
		return false;
	}
	
	

}
