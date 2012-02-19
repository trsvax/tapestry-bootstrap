(function($){
    /** Container of functions that may be invoked by the Tapestry.init() function. */
    $.extend(Tapestry.Initializer, {
    	jqSortable: function(specs){
    		$( specs.selector ).sortable(specs.params);
    		$( specs.selector  ).bind( "sortupdate", function(event, ui) {
				var order = $(this).sortable('toArray').toString();
				$.get(specs.BaseURL,{order:order});
    		});
        }
    });
})(jQuery);