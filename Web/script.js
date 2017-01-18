var address = '/measurements/getLast';
var dataLength = 100;

function printPlot(measurements) {
	var d1 = [];
	
	for(var i = 0; i < measurements.X.length; i++)
	{
		d1.push([measurements.X[i], measurements.Y[i]]);
	}
	

	$.plot("#plot", [ d1 ], {
		xaxis : {
			min: -100,
			max: 100,
			label: 'X'
		},
		yaxis : {
			min: -100,
			max: 100,
			label: 'Y'
		}
	});

	var timeout = $( "#refreshingSlider" ).slider( "option", "value" );
	console.log(timeout);
	setTimeout(getMeasurements, timeout);

}

function getMeasurements() {
	$.ajax({
		url : address,
		method : 'GET',
		data: {
			length : dataLength
		},
		success: function(response) {
			printPlot(response);			
		}
	});
}

$(document).ready(function(){
	$( "#refreshingSlider" ).slider(
	{
		step: 10,
		min: 10,
		max: 1000,
		value: 500
	});
	
	getMeasurements();
});