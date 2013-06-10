define(["jquery", "bootstrap"], function($) {
	var exports;



	return exports =  {	
			popover: function(id,options) {
				$('#'+id).popover(options);
			},
			tooltip: function(id,options) {
				$('#'+id).tooltip(options);
			},
			infinitescroll: function(id,url,options) {
				$('#'+id).infinitescroll($.extend(true,{path: function(index) {
					return url.replace("#index#", index);
				}},options));
			}
	};

});
