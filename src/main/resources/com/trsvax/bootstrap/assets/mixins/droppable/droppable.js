(function($){
    /** Container of functions that may be invoked by the Tapestry.init() function. */
    $.extend(Tapestry.Initializer, {
    	jqDroppable: function(specs){
    		$( specs.selector ).droppable(specs.params);
    		$( specs.selector  ).bind( "drop", function(event, ui) {
    			 var contexte=$(ui.draggable).attr('id');
    			 if ( specs.zoneSelector ) {
	    			 var element = $(specs.zoneSelector);
	    			 var urlWithContexte =specs.BaseURL + "&drop=" + contexte + "&target=" + event.currentTarget.id;
	    			 element.tapestryZone("update" , {url : urlWithContexte});
    			 } else {
    				 var urlWithContexte =specs.BaseURL + "&drop=" + contexte + "&target=" + event.currentTarget.id;
    				 $.get(urlWithContexte).success(
    							function(data) {
    								if (data.redirectURL) {
    					                // Check for complete URL.
    					                if (/^https?:/.test(data.redirectURL)) {
    					                    window.location = redirectURL;
    					                    return;
    					                }				                
    					                window.location.pathname = data.redirectURL;
    					            }
    							}
    					);
    			 }
    		});
        }
    });
})(jQuery);