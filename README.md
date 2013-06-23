 # Tapestry 5 Twitter Bootstrap Module

## List Of Contributors
- Barry Books as trsvax

## Background
[Bootstrap](http://twitter.github.com/bootstrap/) is a grid based CSS framework started by Twitter.
Tapestry is a Java based web framework.

## Features
- Converts existing components such a BeanEditForm and Grid into Bootstrap Forms/Tables
- Components such as ButtonGroup that make creating Bootstrap HTML easier.
- Uses Tapestry chain of command so functionality can be extended/overridden.
- Extensive configuration via the environment

## Basic operation
The module attaches a mixin called FW to all existing components. The mixin calls a chain of command with render events.
In general the cleaupRender phase is used to modify the DOM created by components so that it is compatible with Bootstrap
CSS.

Bootstrap Components:

- **Breadcrumb**
	- format [Breadcrumbs](https://github.com/argoyle/tapestry-breadcrumbs)
	
- **ButtonGroup**
	- Turns a set of links into a [ButtonGroup](http://twitter.github.com/bootstrap/components.html#buttonGroups)
	
- **Code**
   - based on [Code](http://twitter.github.com/bootstrap/base-css.html#code)
   
- **DropDown**
   - marks a set of links so they are formated as a drop down. Can be used in Nav and ButtonGroup
   
- **Nav**
   - based on [Nav](http://twitter.github.com/bootstrap/components.html#navs)
   
- **NavBar**
   - based on [NavBar](http://twitter.github.com/bootstrap/components.html#navbar)
   
- **Pagination**
   - based on [Pagination](http://twitter.github.com/bootstrap/components.html#pagination)

- **Thumbnail**
   - based on [Thumbnails](http://twitter.github.com/bootstrap/components.html#thumbnails)
   
Bootstap Mixins

- **Draggable**
   - makes elements [Draggable](http://jqueryui.com/demos/draggable/)
   
- **Droppable**
   - makes elements [Droppable](http://jqueryui.com/droppable/)
   
- **FW**
   - internal mixin for attaching framework chain to component

- **LoopEnviromentMixin*
   - add to Loop component to support paging
   
- **Pager**
  - integrates Pagination and Loop to implement paged data sets
  
- **PushEnvironment**
  - push new objects into the environment
  
- **Sortable**
  - makes elements [Sortable](http://jqueryui.com/demos/sortable/)
  

##Using Bootstrap:
Just include the jar file to your project, add the following to your Layout.

	@Exclude(stylesheet={"core"})  //If you do not want Tapestry CSS
	@Import(stylesheet={
			"classpath:/com/trsvax/bootstrap/assets/bootstrap/css/bootstrap.css",
			"classpath:/com/trsvax/bootstrap/assets/bootstrap/css/bootstrap-responsive.css"
			},
	library={
			"classpath:/com/trsvax/bootstrap/assets/bootstrap/js/bootstrap.js"
			}
	)
	
then modify your Layout.tml [Layouts](http://twitter.github.com/bootstrap/scaffolding.html#layouts)

	<body>
	  <div class="container">
	   <t:body/>
	  </div>
	</body>


## Using Grid
The fw mixins adds an fwtype parameter to the Grid. If this value is null the Grid works as before. 
Providing a [Table Option](http://twitter.github.com/bootstrap/base-css.html#tables) will cause the
table to be formated by Bootstrap

## Using BeanEditForm
as with Grid an fwtype parameter will format the form with Bootstrap. 
based on [Forms](http://twitter.github.com/bootstrap/base-css.html#forms)

## Using BeanDisplay
Add fwtype parameter to the BeanDisplay. If the value is null the BeanDisplat looks as before.
Use values "dl" or "dl-horizontal" to apply mixin.
Any other CSS classes can be additionally specified here, i.e., bootstrap "well".

## Using Alerts
Add fwtype parameter to the Alerts. If the value is null the Alerts looks as before.
Value fwtype="alert" changes look and feel according to [bootstrap alerts](http://twitter.github.com/bootstrap/components.html#alerts).

Until [TAP5-1996](https://issues.apache.org/jira/browse/TAP5-1996) fixed Tap5 alerts will use this mapping:
Tap5 'info' -> 'alert-success'
Tap5 'warn' -> 'alert'
Tap5 'error' -> 'alert-error'


## Customizing Bootstrap:
There are two Tapestry configurations that allow you to customize Bootstrap. First the EnvironmentSetup.class defines
a set of mapped objects that set defaults for most of the components/mixins. For example the following sets the default
table type to "table" which will cause all Grid tables to be formated by Bootstrap.
 
	@Contribute(EnvironmentSetup.class)
	public static void provideEnvironmentSetup(MappedConfiguration<Class, Object> configuration) {
		configuration.override(TableEnvironment.class, new TableValues(null).withType("table"));
	}


Second is the BootstrapProvider chain.
This contains an ordered list of provides that create the Bootstrap Provider. The chain is called by the FW mixin for
every component. You can override existing providers or add your own.



