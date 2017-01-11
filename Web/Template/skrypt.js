// Definicja funkcji
var json = '[{"id":"B0A0","v":1791.757,"n":"SCAN0.TRANSMISSION","d":"Path Transmission Channel 0","l":16},{"id":"B0A1","v":0.09,"n":"SCAN0.GAIN_TIA","d":"Gain TIA RX Channel 0","l":8},{"id":"B0A2","v":0.056,"n":"SCAN0.REMOTERX.TRANSMISSION","d":"Fiber transmission Channel 0","l":16}]';

var object = JSON.parse(json);

var jsonObject = new Object();
jsonObject['id']='B0A0';
jsonObject['v']=1791.757;
jsonObject['n']='SCAN0.TRANSMISSION';
jsonObject['d']='Path Transmission Channel 0';
jsonObject['l']=16;

jsonStringObject = JSON.stringify(jsonObject);

console.log(object);
console.log(jsonStringObject);

function showStructure(obj) {
	var spectObj = obj.spectrums;
	var text = '</br>';
	for (var i=0; i < spectObj.length; i++) {
		text += 'Id:' + spectObj[i].id + '</br>';
		text += 'N: ' + spectObj[i].n+ '</br>';
		text += 'D:' + spectObj[i].d + '</br>';
		text += 'I:' + spectObj[i].i + '</br>';
		text += 'L:' + spectObj[i].l + '</br>';
		text +='V: ';
		for(var j=0; j<spectObj[i].v.length; j++) {
			text += spectObj[i].v[j] + ', ';
		}
		text += '</br></br>';
	}
	$("#spectrums").html(text);
}

function showMeasurements(obj) {
	var spectObj = obj.measurements;
	
	var text = '';
	
	for(var i=0; i<spectObj.length; i++) {
			text += '<tr>';
			text += '<td>' + spectObj[i].id + '</td>';
			text += '<td>' + spectObj[i].v + '</td>';
			text += '<td>' + spectObj[i].n + '</td>';
			text += '<td>' + spectObj[i].d + '</td>';
			text += '<td>' + spectObj[i].l + '</td>';
			text += '</tr>';
	}
	
	$("tbody").html(text);
	
	setTimeout(getAjax('systemStructure.php'), 10000);
}

function getAjax(urlVal) {
	var jsonObj;
	$.ajax({
		url: urlVal,
		success: function(result){
			jsonObj = JSON.parse(result);
			showMeasurements(jsonObj);
		}
	});
}

// Odwo³anie do dokumentu
$( document ).ready(function() {
		
	// Obs³uga przycisku
	$('#test').click(function(){	
	});		
	
	setTimeout(getAjax('systemStructure.php'), 10000);
});