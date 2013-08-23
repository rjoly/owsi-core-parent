

!function( $ ) {
	"use strict"
	
	$.fn.alert = {
		show: function(selector, autohideDelay) {
			var $selector = $(selector);
			var $panel = $(".alert-global-panel", $selector);
			$panel.off("click.alert");
			if ($(".alert-info, .alert-warning, .alert-error, .alert-success" , $panel).size() > 0) {
				$panel.animate({ top: 0 }, 500);
				
				if ($(".alert-info, .alert-warning, .alert-error", $panel).size() == 0) {
					$panel.on("click.alert", function(event) { $.fn.alert.close(event); });
					if (autohideDelay == null) {
						autohideDelay = 5000;
					}
					if (autohideDelay >= 0) {
						$panel.delay(autohideDelay).animate({ top: -$panel.outerHeight() });
					}
				}
			}
		},
		hide: function(selector) {
			var $selector = $(selector);
			var $panel = $(".alert-global-panel", $selector);
			if ($(".alert-info, .alert-warning, .alert-error, .alert-success" , $panel).size() > 0) {
				$panel.css({ top: -$panel.outerHeight() });
			}
		},
		reset: function(selector, autohideDelay) {
			$.fn.alert.hide(selector);
			$(".alert-global-panel", $(selector)).show();
			$.fn.alert.show(selector, autohideDelay);
		},
		close: function(event) {
			var $panel = $(event.target).closest(".alert-global-panel");
			if ($(".alert-info, .alert-warning, .alert-error, .alert-success" , $panel).size() > 0) {
				$panel.stop(true).animate({ top: -$panel.outerHeight() });
			}
			event.preventDefault();
		}
	};
}( window.jQuery )