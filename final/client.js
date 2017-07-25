var socket = require('socket.io-client')('https://amedeo.informatik.rwth-aachen.de/');
var fs = require('fs');
var filePath = "disco.conf";

console.log("Running");

socket.on('connect', function() {
	console.log("connected");
});

socket.on('disco', function(data) {
	disco = data.disco_state;
	
	if (disco) {
		fs.closeSync(fs.openSync(filePath, 'w'));
	} else {
		fs.unlinkSync(filePath);
	}
});

socket.on('disconnect', function(){});

