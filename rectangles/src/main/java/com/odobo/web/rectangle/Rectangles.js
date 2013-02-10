;
(function(undefined) {
	"use strict";

	if (typeof (Odobo) === 'undefined') {
		window.Odobo = {};
	}

	Odobo.createRectagle = function(canvasId, rectangle) {
		 var canvas = document.getElementById(canvasId);
	      var context = canvas.getContext('2d');
	
	      context.beginPath();
	      context.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	      context.fillStyle = 'yellow';
	      context.fill();
	      context.lineWidth = 1;
	      context.strokeStyle = 'black';
	      context.stroke();
	}
	
	Odobo.createRectagles = function(canvasId, json) {
		for(var i=0; i < json.sourceRectangles.length; i++) {
			Odobo.createRectagle(canvasId, json.sourceRectangles[i]);
		}		
	}

})();