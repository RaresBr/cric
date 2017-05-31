function geoFindMe2() {
	 document.getElementById("formId:x").value = 4.0;
	    document.getElementById("formId:y").value = 35;
	function success(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;

		 document.getElementById("formId:x").value = 4.0;
		    document.getElementById("formId:y").value = 35;
	}
	navigator.geolocation.getCurrentPosition(success, error);
}
function geoFindMe() {
	function success(position) {
		
		alert('succ');
		document.getElementById("formId:x").value = latitude;
	    document.getElementById("formId:y").value = longitude;
	    document.getElementById('formId').submit();
		output.innerHTML = '<p>Latitude is ' + latitude
				+ '° <br>Longitude is ' + longitude + '°</p>';

	}

	function error() {
		alert('error');
	
	}

	navigator.geolocation.getCurrentPosition(success, error);
}