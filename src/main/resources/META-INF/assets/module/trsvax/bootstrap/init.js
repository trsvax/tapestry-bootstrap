define(["jquery", "bootstrap"], function($) {
	var exports;



	return exports =  {	
			popover: function(id,options) {
				$('#'+id).popover(options);
			},
			tooltip: function(id,options) {
				$('#'+id).tooltip(options);
			}
	};

});


