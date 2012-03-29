package com.trsvax.bootstrap.services.bootstrapvisitors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Visitor;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.slf4j.Logger;

import com.trsvax.bootstrap.FrameworkMixin;
import com.trsvax.bootstrap.FrameworkVisitor;
import com.trsvax.bootstrap.environment.FrameWorkEnvironment;

public class BootstrapFrameworkVisitor implements FrameworkVisitor {
	public final static String id = "fw";
	private final Logger logger;	
	private final Environment environment;
	private final AssetSource assetSource;
	private final String jQueryAlias;
	private String ns = "fw";
	private String prefix = ns + ".";
		
	public BootstrapFrameworkVisitor(Logger logger, Environment environment,
			AssetSource assetSource, @Symbol(JQuerySymbolConstants.JQUERY_ALIAS) String alias) {
		this.logger = logger;
		this.environment = environment;
		this.assetSource = assetSource;
		this.jQueryAlias = alias;
	}
	
	public void beginRender(FrameworkMixin component, MarkupWriter writer) {
		String simpleName = component.getComponentResources().getContainer().getClass().getSimpleName();
		logger.info("begin");
		Transform transform = getTransformer(simpleName);
		if ( transform != null ) {
			Element tag = writer.elementNS(ns, prefix + simpleName);
			tag.attribute("type", component.getType());
			for ( Entry<String, Object> param : component.getParms().entrySet() ) {
				if ( param.getValue() != null ) {
					tag.attribute(param.getKey(),param.getValue().toString());
				}
			}
			transform.beginRender(component, writer);	
		}
	}

	public void afterRender(FrameworkMixin component, MarkupWriter writer) {
		String simpleName = component.getComponentResources().getContainer().getClass().getSimpleName();
		Transform transform = getTransformer(simpleName);
		if ( transform != null ) {
			logger.info("visit {}",simpleName);
			transform.afterRender(component, writer);
			addHelp(writer.getElement(), component.getComponentResources().getPage().getComponentResources().getMessages());
			writer.end();		
		}
	}

	public void visit(Element element) {
		final Set<Element> pop = new HashSet<Element>();
		element.visit(new Visitor() {
				public void visit(Element element) {
					if ( ns.equals(element.getNamespace())) {
						pop.add(element);
						String name = element.getName().replace(prefix, "");							
						Transform transform = getTransformer(name);
						if ( transform != null ) {
							
							//logger.info("visit {}",element.getName());
	
							transform.visit(element);	
							
						}
					}
				}
			});

		for ( Element e : pop ) {
			logger.info("pop {}",e.getName());
				e.pop();
		}
	}
	
	interface Transform {		
		void beginRender(FrameworkMixin component, MarkupWriter writer);
		void afterRender(FrameworkMixin component, MarkupWriter writer);
		void visit(Element element);
	}
	
	class BeanEditForm implements Transform {
		Element root;
		Element buttonContainer;
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			this.root = element;
			root.visit(beanEditForm());
			if ( buttonContainer != null && root.getAttribute("buttons") != null ) {
				String[] buttons = root.getAttribute("buttons").split(",");
				for ( String button : buttons ) {
					Element buttonElement = root.getDocument().getElementById(button);
					buttonElement.moveToBottom(buttonContainer);
				}

				
			}
		}
			
		Visitor beanEditForm() {	
			return new Visitor() {
	
				public void visit(Element element) {
					if (hasClass("t-beaneditor", element)) {
						pop(element);
						element.visit(beanEditForm());
						element.pop();
					}
					if (hasClass("t-beaneditor-row", element)) {
						element.forceAttributes("class", "control-group");
					}
					if ( input(element)) {
						
						String type= element.getAttribute("type");
						String value = element.getAttribute("value") == null ? "" : element.getAttribute("value") ;
						if ( type != null && type.equals("submit") && ! value.equals("Cancel") ) {
							buttonContainer = element.getContainer();
							buttonContainer.forceAttributes("class","form-actions");
							element.addClassName("btn btn-primary");
						} else if ( value.equals("Cancel")) {
							element.addClassName("btn");
						} else {
							element.wrap("div", "class", "control");
						}
					}
					if ( label(element)) {
						element.addClassName("control-label");
					}
					if ( img(element)) {
						element.remove();
					}
				}
			};
		}


	}
	
	class Grid implements Transform {
		Element root;
		List<Element> poplist = new ArrayList<Element>();
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			this.root = element;
			String framework = root.getAttribute("fw");
			if ( "tapestry".equals(framework)) {
				return;
			}
			root.visit(grid());
			for ( Element e : poplist ) {
				e.pop();
			}
			GridPager gridPager = new GridPager();
			gridPager.visit(root);
		}
				
		Visitor grid() {
			return new Visitor() {
				
				public void visit(Element element) {
					String className = root.getAttribute("type");
					if ( table(element)) {
						if ( className != null ) {
							element.addClassName(className);
						}
					}
					if ( div(element) && hasClass("t-data-grid", element)) {
						poplist.add(element);
					}
					if ( hasName("fw.PageLink", element) || hasName("fw.EventLink",element)) {
						new Link().visit(element);
						poplist.add(element);
					}
					if ( img(element) && hasClass("t-sort-icon", element) ) {
							element.elementBefore("span").text("^");
							element.remove();						
					}
					
					// Tapestry puts property names on cells
					// add fw- to avoid css name conflicts
					if ( hasClass(element) && (td(element) || th(element)) ) {
						String[] classes = element.getAttribute("class").split(" ");
						String newClass = "";
						for ( String s : classes ) {
							newClass += " fw-" + s;
						}
						element.forceAttributes("class",newClass);
						
					}
				}
			};
		}
	}
	
	class GridPager implements Transform {
		Element root;
		Element ul;
		Element div;
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			element.visit(new Visitor() {
				
				public void visit(Element element) {
					if ( hasName("fw.gridpager", element)) {
						root = element;
					}				
				}
			});
			if ( root != null ) {
				root.visit(gridPager());
				if ( ul != null ) {
					ul.moveToBottom(div);
				}
				//root.pop();
			}
		}
		
		Visitor gridPager() {
			return new Visitor() {
				
				public void visit(Element element) {
					if ( hasClass("t-data-grid-pager", element)) {
						div = element;
						element.forceAttributes("class","pagination");
						ul = element.elementBefore("ul");
					}
					if ( anchor(element) ) {
						if ( ul != null ) {
						Element li = element.wrap("li");
						
							li.moveToBottom(ul);
						}
					}
					if ( span(element)) {
						if ( ul != null ) {
						Element a = element.wrap("a","href","#");
						Element li = a.wrap("li");
						
							li.moveToBottom(ul);
						}
					}
					
					
				}
			};
		}
		
	}

	class Nav implements Transform {
		Element root;
		List<Element> poplist = new ArrayList<Element>();
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-button.js");
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-dropdown.js");
			scriptOnce(String.format("%s('.makeHash').attr('href','#');",jQueryAlias));
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			this.root = element;
			root.visit(nav());
			for ( Element e : poplist ) {
				e.pop();
			}
			root.visit( new Visitor() {
				
				public void visit(Element element) {
					if ( hasName("fw.PageLink", element)) {
						element.pop();
					}
				}
			});
		}
		
		Visitor nav() {
			//logger.info("nav");
			return new Visitor() {
				

				Integer activeLink = 0;
				Integer linkCounter = 1;
				boolean tabbable = false;
				Element ul;

				public void visit(Element element) {
					if (hasName("fw.Nav", element)) {
						String type = element.getAttribute("type");
						if ( type == null ) {
							type = "";
						}
						if ( type.contains("tabbable")) {
							type = type.replace("tabbable", "");
							tabbable = true;
						}
						ul = element.wrap("ul", "class", "nav " + type);
						if ( tabbable ) {
							ul.wrap("div", "class","tabbable");
						}
						
					}
					if ( hasName("fw.ComboButton",element)) {
						element.wrap("li","class","dropdown");
						element.visit(comboButtonNav());
						element.pop();
					}
					if (anchor(element)) {
						if ( isActive(wrapLI(element))) {
							activeLink = linkCounter;
						}
						linkCounter++;
						
					}
					if ( hasName("fw.Content", element)) {
						element.visit(tabContent(activeLink));
						Element div = element.wrap("div","class","tab-content");
						div.moveAfter(ul);
						element.pop();
						
					}
					if ( hasName("fw.PageLink",element) || hasName("fw.EventLink", element)) {
						//logger.info("pop {}",element.getName());
						poplist.add(element);
					}
				}
			};
		}
		
		Visitor tabContent(final Integer activeLink) {
			return new Visitor() {
				Integer linkCounter = 1;
				public void visit(Element element) {
					if ( div(element)) {
						element.addClassName("tab-pane");
						if ( linkCounter == activeLink ) {
							element.addClassName("active");
						}
						element.attribute("id", (linkCounter++).toString());
					}
					
				}
			};
		}
		
		Visitor comboButtonNav() {
			return new Visitor() {
				boolean first = true;
				public void visit(Element element) {
					if ( hasName("fw.DropDown",element) ) {
						element.wrap("ul","class","dropdown-menu");
						new Dropdown().visit(element);
						element.pop();
					}
					if ( first && anchor(element)) {
						element.addClassName("dropdown-toggle makeHash");
						element.attribute("data-toggle", "dropdown");
						element.element("b", "class","caret");
						first = false;
					}	

				}
			};
		}
	}
	
	class ButtonGroup implements Transform {
		Element root;
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			this.root = element;
			root.visit(buttonGroup());
		}
		Visitor buttonGroup() {
			return new Visitor() {
				public void visit(Element element) {
					if (hasName("fw.ButtonGroup", element)) {
						element.wrap("div", "class", "btn-group");
					}
					if (anchor(element)) {
						element.addClassName("btn");
					}
				}
			};
		}
	}
	
	class ComboButton implements Transform {
		Element root;
		
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-button.js");
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-dropdown.js");
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void visit(Element element) {
			this.root = element;
			root.visit(comboButton());
		}
		
		Visitor comboButton() {
			return new Visitor() {
				boolean first = true;
				public void visit(Element element) {
					if ( hasName("fw.ComboButton",element) ) {
						Element div = element.wrap("div", "class","btn-group");
						pop(element);
						div.visit(comboButton());
						
					}
					if ( hasName("fw.DropDown",element) ) {
						Element ul = element.wrap("ul","class","dropdown-menu");
						Element caret = ul.elementBefore("a", "class","btn dropdown-toggle","data-toggle","dropdown","href","#");
						caret.element("span", "class","caret");
						new Dropdown().visit(element);
						element.pop();
					}
					if ( first && anchor(element)) {
						element.addClassName("btn");
						first = false;
					}					
				}
			};
		}
	}
	
	class NavBar implements Transform {
		Element root;
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js.js");
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-dropdown.js");
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-scrollspy.js");
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void visit(Element element) {
			this.root = element;
			root.visit(navBar());
		}
		
		Visitor navBar() {
			return new Visitor() {
				
				public void visit(Element element) {
					if ( hasName("fw.NavBar", element)) {
						String projectName = element.getAttribute("ProjectName");
						String type = element.getAttribute("type");
						element.wrap("div", "class","container").wrap("div","class","navbar-inner").wrap("div","class","navbar " + type);
						if ( projectName != null ) {
							element.elementBefore("a", "class","brand","href","#").text(projectName);
						}
					}
					if ( hasName("fw.Nav",element)) {
						new Nav().visit(element);
						element.pop();
					}
					if ( form(element)) {
						element.addClassName("navbar-search");
					}
					if ( input(element)) {
						element.addClassName("search-query");
					}			
				}
			};
		}
	}
	
	class Breadcrumb implements Transform {
		Element root;
		Element lastLi = null;

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void visit(Element element) {
			this.root = element;
			root.wrap("ul", "class","breadcrumb");
			root.visit(breadCrumb());
			if ( lastLi != null ) {
				lastLi.addClassName("active");
			}
		}
		
		Visitor breadCrumb() {
			return new Visitor() {

				public void visit(Element element) {
					if ( anchor(element) ) {
						Element li = element.wrap("li");
						if ( lastLi != null  ) {
							lastLi.element("span", "class","divider").text("/");
						}
						lastLi = li;
					}				
				}
			};
		}
	}
	
	
	class Dropdown implements Transform {
		Element root;
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-button.js");
			//importJavaScript("/com/trsvax/bootstrap/pages/twitter/js/bootstrap-dropdown.js");
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			this.root = element;
			root.visit(dropdown());
			root.visit( new Visitor() {
				
				public void visit(Element element) {
					if ( hasName("fw.PageLink", element)) {
						element.pop();
					}
				}
			});
		}
		
		Visitor dropdown() {
			return new Visitor() {
				
				public void visit(Element element) {
					if ( anchor(element)) {
						wrapLI(element);
					}
				}
			};
		}
	}
	
	class Thumbnails implements Transform {
		Element root;
		public void beginRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {
			root = element;			
			wrap(root,"ul").addClassName("thumbnails");
			root.visit(thumbnails());			
		}

		Visitor thumbnails() {
			return new Visitor() {
				
				public void visit(Element element) {
					if ( img(element) || hasName("fw.Thumbnail",element)) {
						String span = element.getAttribute("span");
						String id = element.getAttribute("id");
						if ( span != null ) {
							element.wrap("a","href","#","class","thumbnail")
								.wrap("li","class","span" + span,"id",id);
						}
					}
					if ( hasName("fw.Thumbnail",element)) {
						element.pop();
					}
				}
			};
		}	
	}
	
	class Thumbnail implements Transform {

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}

		public void visit(Element element) {			
		}
		
	}
	
	class Content implements Transform {

		public void visit(Element element) {			
		}

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
	}
	
	class Link implements Transform {
		Element root;

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {	
			root = writer.getElement();
			root.visit( new Visitor() {				
				public void visit(Element element) {
					logger.info("link {}",element.getName());
					if ( anchor(element) ) {
						String type = root.getAttribute("type");
						if ( type != null ) {
							element.addClassName(getClassForType(type));
						}
					}
					
				}
			});
		}

		public void visit(Element element) {
			
		}		
	}
	
	class LinkSubmit implements Transform {
		Element root;

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {	
			root = writer.getElement();
			root.visit( new Visitor() {				
				public void visit(Element element) {
					logger.info("link {}",element.getName());
					if ( span(element) ) {
						String type = root.getAttribute("type");
						if ( type != null ) {
							element.addClassName(getClassForType(type));
						}
					}
					
				}
			});
		}

		public void visit(Element element) {
			
		}		
	}
	
	class Submit implements Transform {
		Element root;

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {
			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {	
			root = writer.getElement();
			root.visit( new Visitor() {				
				public void visit(Element element) {
					logger.info("link {}",element.getName());
					if ( input(element) ) {
						String type = root.getAttribute("type");
						if ( type != null ) {
							element.addClassName(getClassForType(type));
						}
					}
					
				}
			});
		}

		public void visit(Element element) {
			
		}		
	}
	
	class Any implements Transform {
		Element root;

		public void visit(Element element) {			
		}

		public void beginRender(FrameworkMixin component, MarkupWriter writer) {			
		}
		
		public void afterRender(FrameworkMixin component, MarkupWriter writer) {	
			root = writer.getElement();
			root.visit( new Visitor() {				
				public void visit(Element element) {
					if ( !hasName("fw.any", element) ) {
						String type = root.getAttribute("type");
						if ( type != null ) {
							element.addClassName(getClassForType(type));
						}
					}
					
				}
			});
		}
		
	}
	
	String getClassForType(String type) {
		if ( type.startsWith("btn-")) {
			boolean hasButton = false;
			String[] types = type.split(" ");
			for ( String t : types ) {
				if ( t.equals("btn")) {
					hasButton = true;
				}
			}
			if ( ! hasButton ) {
				type = "btn " + type;
			}

		}
		return type;
	}
		
	
	Transform getTransformer(String name) {
		Transform transform = null;
		
		if ( "BeanEditForm".equals(name) ) {
			transform = new BeanEditForm();
		} else if ("Grid".equals(name)) {
			transform = new Grid();
		} else if ("EventLink".equals(name) || "PageLink".equals(name) || "ActionLink".equals(name) ) {
			transform = new Link();
		} else if ("LinkSubmit".equals(name)) {
			transform = new LinkSubmit();
		} else if ("Submit".equals(name)) {
			transform = new Submit();
		} else if ("Nav".equals(name)) {
			transform = new Nav();
		} else if ("ButtonGroup".equals(name)) {
			transform = new ButtonGroup();
		} else if ( "ComboButton".equals(name)) {
			transform = new ComboButton();
		} else if ( "NavBar".equals(name)) {
			transform = new NavBar();
		} else if ( "Breadcrumb".equals(name)) {
			transform = new Breadcrumb();
		} else if ( "DropDown".equals(name)) {
			transform = new Dropdown();
		} else if ("Content".equals(name)) {
			transform = new Content();
		} else if ("Thumbnails".equals(name)) {
			transform = new Thumbnails();
		} else if ("Thumbnail".equals(name)) {
			transform = new Thumbnail();
		} else if ( "GridPager".equals(name)) {
			transform = new GridPager();
		} else if ( "Any".equals(name)) {
			transform = new Any();
		}
		
		return transform;
		
	}
	
	
	
	
	boolean span(Element element) {
		return hasName("span", element);
	}
	boolean anchor(Element element) {
		return hasName("a", element);
	}
	boolean input(Element element) {
		return hasName("input", element);
	}
	boolean label(Element element) {
		return hasName("label", element);
	}
	boolean img(Element element) {
		return hasName("img", element);
	}
	boolean table(Element element) {
		return hasName("table", element);
	}
	boolean div(Element element) {
		return hasName("div", element);
	}
	boolean form(Element element) {
		return hasName("form", element);
	}
	boolean th(Element element) {
		return hasName("th", element);
	}
	boolean td(Element element) {
		return hasName("td", element);
	}

	boolean hasName(String name, Element element) {
		if ( isPopped(element) ) {
			return false;
		}
		if (element.getName().toLowerCase().equals(name.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	boolean hasClass(Element element) {
		return element.getAttribute("class") != null;
	}

	boolean hasClass(String className, Element element) {
		if ( isPopped(element) ) {
			return false;
		}
		String c = element.getAttribute("class");
		if (c == null || className == null || c.length() == 0
				|| className.length() == 0) {
			return false;
		}
		String[] classes = c.split(" ");
		for (String s : classes) {
			if (className.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	void pop(Element element) {
		element.attribute("popped", "true");
	}
	
	boolean isPopped(Element element) {
		String pop = element.getAttribute("popped");
		if ( pop == null ) {
			return false;
		}
		if ( pop.equals("true")) {
			return true;
		}
		return false;
	}
	
	boolean isActive(Element element) {	
		String active = element.getAttribute("active");
		if (active != null && active.equals("true")) {
			return true;
		}
		return hasClass("active", element);
	}

	Element wrapLI(Element element) {
		Element li = element.wrap("li");
		if ( isActive(element)) {
			li.addClassName("class", "active");
		}
		return li;
	}
	
	Element wrap(Element root, String elementName) {
		Element element = root.wrap(elementName);
		if ( hasClass(root)) {
			element.addClassName(root.getAttribute("class"));
		}
		String id = root.getAttribute("id");
		if ( id != null ) {
			element.forceAttributes("id",id);
		}
		return element;
	}
	
	void addHelp(Element element, final Messages messages) {
		element.visit( new Visitor() {
			public void visit(Element element) {
				if (element.getName().equals("input")) {
					String name = element.getAttribute("name");
					if (name != null && messages.contains(name + "-help")) {
						String help = messages.get(name + "-help");
						element.element("div", "class", "help-block").text(help);
					}
				}
			}
		});
	}
	
	void importJavaScript(String lib) {
		JavaScriptSupport javaScriptSupport = environment.peek(JavaScriptSupport.class);
		Asset asset = assetSource.getClasspathAsset(lib);
		javaScriptSupport.importJavaScriptLibrary(asset);
	}
	
	void scriptOnce(String script) {
		FrameWorkEnvironment excludeEnvironment = environment.peek(FrameWorkEnvironment.class);
		excludeEnvironment.addScriptOnce(script);
	}

}
