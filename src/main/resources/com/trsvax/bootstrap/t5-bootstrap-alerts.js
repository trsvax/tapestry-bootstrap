(function( $ ) {
    T5.extendInitializers(function() {
        
        var DISMISS_ALERTS = "tapestry:dismiss-all";
        
        function makeBootstrapAlert($alert, addClass) {
            if ($alert.hasClass("alert")) {
                return;
            }
            
            $alert.removeClass();
            
            $alert.addClass("alert" + (addClass == "" ? "" : " ") + addClass);
            
            var $dismiss = $alert.children(".t-dismiss").first();
            
            $dismiss.removeClass();
            
            $dismiss.addClass("close");
            
            $dismiss.text("×");
        }
        
        function bootstrapAlerts(spec) {
            T5.sub(T5.events.ADD_ALERT, null, function(alertSpec) {
                var $alerts = $(".t-alert-container").children();
                
                $alerts.filter("DIV.t-error").each(function(){
                    makeBootstrapAlert($(this), "alert-error");
                });
                $alerts.filter("DIV.t-warn").each(function(){
                    makeBootstrapAlert($(this), "");
                });
                $alerts.filter("DIV.t-info").each(function(){
                    makeBootstrapAlert($(this), "alert-info");
                });
                $alerts.filter("DIV.t-success").each(function(){
                    makeBootstrapAlert($(this), "alert-success");
                });
                
                var $controls = $(".t-alert-controls");
                
                if ($controls.length == 0) {
                    //  Already moved
                    return;
                }
                
                $controls.removeClass();
                $controls.addClass("t5-bootstrap-alert-controls");
                
                var $dismissAll = $controls.children("a").first();
                $dismissAll.addClass("close t5-bootstrap-dismiss-all");
                
                var $parent = $controls.parent();
                $controls.detach().prependTo($parent);
            });
        }

        return {
            bootstrapAlerts : bootstrapAlerts
        };
        
    });

})(jQuery);
