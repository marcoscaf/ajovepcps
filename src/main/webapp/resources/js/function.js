function clearField(idField) {
	var inputs = document.getElementsByTagName("input");
	var selects = document.getElementsByTagName("select");
	var labels = document.getElementsByTagName("label");
	/* var textAreas = document.getElementsByTagName("label"); */

	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].id.indexOf(idField) >= 0) {
			inputs[i].value = "";
			break;
		}
	}

	for (var i = 0; i < selects.length; i++) {
		if (selects[i].id.indexOf(idField) >= 0) {
			selects[i].value = "";
			break;
		}
	}

	for (var i = 0; i < labels.length; i++) {
		if (labels[i].id.indexOf(idField) >= 0) {
			labels[i].innerHTML = "...";
			break;
		}
	}
}

function clearFields(elementId) {
	var element = document.getElementById(elementId);
	var inputs = element.getElementsByTagName("input");
	var selects = element.getElementsByTagName("select");
	var labels = document.getElementsByTagName("label");

	/* var textAreas = document.getElementsByTagName("label"); */

	for (var i = 0; i < inputs.length; i++) {

		if (inputs[i].id.indexOf('ipt') >= 0) {
			inputs[i].value = "";
		}
	}

	for (var i = 0; i < selects.length; i++) {

		if (selects[i].id.indexOf('ipt') >= 0) {
			selects[i].value = "";
		}
	}

	for (var i = 0; i < labels.length; i++) {

		if (labels[i].id.indexOf('ipt') >= 0) {
			labels[i].innerHTML = "...";
		}
	}

	closeMessPanelIfOpen();
}

function handleSubmitRequest(xhr, status, args) {
	if(args.validationFailed == null){
		closeMessPanelIfOpen();
	}
}

function closeMessPanelIfOpen() {

	try {

		setTimeout(
				"document.getElementById('templateForm:globalMess').children[0].children[0].click()",
				4000);

	} catch (err) {

	}
}
