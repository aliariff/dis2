var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);
var path = require('path');
var os = require('os');
var bodyParser = require('body-parser');
var cache_images = {};
var config = {
    port: 500
};

app.use(bodyParser.json({
    limit: '2mb'
})); // support json encoded bodies
app.use(bodyParser.urlencoded({
    limit: '2mb',
    extended: true
})); // support encoded bodies

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.get('/active', function(req, res) {
    data = {
        active_camera: Object.keys(cache_images).length
    }
    res.send(JSON.stringify(data));
});

app.post('/image', function(req, res) {
    sendImage(req.body);
    res.send('Image Received');
});

io.on('connection', function(socket) {
    for (key in cache_images) {
        socket.emit('live-stream', {
            cam_id: key,
            image_base64: cache_images[key]['image']
        });
    }
});

http.listen(config['port'], function() {
    console.log('listening on *:' + config['port']);
});

function sendImage(data) {
    if (data.cam_id && data.image_base64) {
        cache_images[data.cam_id] = {
            image: data.image_base64,
            timestamp: new Date().getTime()
        };
        io.sockets.emit('live-stream', data);
    }
}

setInterval(function() {
    for (key in cache_images) {
        timestamp = cache_images[key]['timestamp'];
        if (new Date().getTime() - timestamp > 10000) {
            delete cache_images[key];
            io.sockets.emit('dead-stream', { cam_id: key });
        }
    }
}, 1000);
