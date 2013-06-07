 # Tapestry 5.4 Twitter Bootstrap Module

## List Of Contributors
- Barry Books as trsvax

## Background
[Bootstrap](http://twitter.github.com/bootstrap/) is a grid based CSS framework started by Twitter.
Tapestry is a Java based web framework.

## Features

- Extensive configuration via the environment

## Basic operation


Bootstrap Components:

- **Pagination**
	- Creates a Bootstrap Pagination Component

- **Row**
	- Creates a Bootstrap row
	
- **Span**
	- Creates a Bootstrap span
	
-- **Thumbnails**
	-- Container for Bootstrap Thumbnail Gallery
	

   
Bootstap Mixins

- **BootstrapFrom**
   - Adapts for output to the various styles of Bootstrap forms. Just add a class name such as form-inline
   
- **PagedLoopMixin**
	- Allows the existing Tapeestry Loop component to handle paging. 
	
- **Popover**
	- Add Bootstrap Popover behavior to an element

- **Tooltip**
	- Add Bootstrap Tooltip behavior to an element
   

  

##Using Bootstrap:
Just include the jar file to your project



## Creating a gallery
<t:tb.thumbnails for="users" rowsPerPage="8" availableRows="users.AvailableRows">
	<t:tb.pagination />
	<ul class="thumbnails">
	<li t:type="loop" t:id="users" value="user" class="span4">
		${user.firstname}
	</li>
	</ul>
	<t:tb.pagination />
</t:tb.thumbnails>








