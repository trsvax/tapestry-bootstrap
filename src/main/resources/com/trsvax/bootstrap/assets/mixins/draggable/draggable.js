(function($){
    /** Container of functions that may be invoked by the Tapestry.init() function. */
    $.extend(Tapestry.Initializer, {
    	jqDraggable: function(specs){
    		$( specs.selector ).draggable(specs.params);   		
        }
    });
})(jQuery);