(function($){
    /** Container of functions that may be invoked by the Tapestry.init() function. */
    $.extend(Tapestry.Initializer, {
    	jqDroppable: function(specs){
    		$( specs.selector ).droppable(specs.params);
    		$( specs.selector  ).bind( "drop", function(event, ui) {
    			 var contexte=$(ui.draggable).attr('id');
    			 if ( specs.zoneSelector ) {
	    			 var element = $(specs.zoneSelector);
	    			 var urlWithContexte =specs.BaseURL + "&drop=" + contexte;
	    			 element.tapestryZone("update" , {url : urlWithContexte});
    			 } else {
    				 var urlWithContexte =specs.BaseURL + "&drop=" + contexte;
    				 $.get(urlWithContexte);
    			 }
    		});
        }
    });
})(jQuery);