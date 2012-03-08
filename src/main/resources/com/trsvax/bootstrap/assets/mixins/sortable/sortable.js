(function($){
    /** Container of functions that may be invoked by the Tapestry.init() function. */
    $.extend(Tapestry.Initializer, {
    	jqSortable: function(specs){
    		$( specs.selector ).sortable(specs.params);
    		$( specs.selector  ).bind( "sortupdate", function(event, ui) {
				var order = $(this).sortable('toArray').toString();
				var target = event.currentTarget.id;
				$.get(specs.BaseURL,{order:order, target:target}).success(
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
    		});
        }
    });
})(jQuery);