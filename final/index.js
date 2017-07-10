var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);
var path = require('path');
var os = require('os');
var bodyParser = require('body-parser');
var cache_images = {};
var config = {
    port: 1234
};

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({
    extended: true
})); // support encoded bodies

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.post('/image', function(req, res) {
    sendImage(req.body);
    res.send('Image Received');
});

io.on('connection', function(socket) {
    for (key in cache_images) {
        socket.emit('live-stream', {
            cam_id: key,
            image_base64: cache_images[key]
        });
    }
});

http.listen(config['port'], function() {
    console.log('listening on *:' + config['port']);
});

function sendImage(data) {
    if (data.cam_id && data.image_base64) {
        cache_images[data.cam_id] = data.image_base64;
        io.sockets.emit('live-stream', data);
    }
}
