

window.onload = function(){
	document.getElementById('send').addEventListener( 'submit', sendForm );
}









function sendForm( event ) {
	console.log('entrou SendForm');
	let formData = new FormData(event.target);
	let url = event.target.action + '?';
	url += '_csrf='+ formData.get('_csrf');
	formData.delete('_csrf');
	let fields = {};
	formData.forEach((value, key) => fields[key] = value)

	postJson(url, JSON.stringify(fields) ).then( response => {
		responseDelegator(response);
		
	})
	event.preventDefault();
	
/*	
	
	formData.forEach( (e, ) => console.log());
	foreach()
	let data = new URLSearchParams(formData);
	loadFlags( "/showFlags?" + data.toString() ).then( data => {
		map.clearOverlays();
		data.forEach( (e) => addMarker(e));
	});
	
*/	
};








async function postJson (url, req) {
	let response = await fetch(url,{
		method: 'post',
//		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
//			'Content-Length': req.length.toString(),
//			'Accept-Encoding': 'gzip, deflate, br',
//			'Connection': 'keep-alive'
		},
		body: req
	});
	let json = await response.json();
	return json;
}

function responseDelegator(response){
	let responder = document.getElementById('responder');
	//Se existir e tiver algo, faça
	if(response.title && response.title !== undefined)
		responder.getElementsByClassName('modal-title')[0].innerHTML = response.title;
	if(response.message && response.message !== undefined)
		responder.querySelector('.modal-body p')[0].innerHTML = response.message;
	if(response.action && response.action !== undefined)
		responder.querySelector('.modal-body p')[0].innerHTML = response.action;
	responder.modal({'show':true});
}


/*



const userAction = async () => {
  const response = await fetch('http://example.com/movies.json', {
    method: 'POST',
    body: myBody, // string or object
    headers: {
      'Content-Type': 'application/json'
    }
  });
  const myJson = await response.json(); //extract JSON from the http response
  // do something with myJson
}

*/