var address = '/measurements/getLast';
var dataLength = 100;
var timeoutTime = 100;

function printPlot(measurements) {
	var array = [[]];
	
	for(var i = 0; i < measurements.length; i++)
	{
		array[0].push([i, measurements[i]]);
	}
	
	var plot1 = $.jqplot('plot', array);
	
	setTimeout(getMeasurements, timeoutTime);
}

function getMeasurements() {
	$.ajax({
		url : address,
		method : 'GET',
		data: {
			length : dataLength
		},
		success: function(response) {
			printPlot(response.measurements);
		}
	});
}

$(document).ready(function(){
	getMeasurements();
});