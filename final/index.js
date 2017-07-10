var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);
var os = require('os');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
// var configLoader = require('config-json');

// configLoader.load('./config.json');

var intervalObj;
// var config = configLoader.get();
var config = {
    port: 1234
};
var process;
var isStreaming = false;

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

// io.origins(config['client-host'] + ':' + config['client-port']);

app.get('/', function (req, res) {
  res.send('Hello From Fridge' + os.hostname())
});

app.post('/image', function(req, res) {
  sendImage(req.body.image);
  res.send('Image Received');
});

http.listen(config['port'], function() {
  console.log('listening on *:' + config['port']);
});

io.on('connection', function(socket) {
    socket.on('start-stream', function() {
        if (!isStreaming) {
            startStreaming();
        } else {
            sendImage();
        }
    });

    socket.on('disconnect', function() {
        if (io.engine.clientsCount == 0) {
            stopStreaming();
        }
    });
});

function sendImage(image) {
    //console.log('sending image');
    io.sockets.emit('live-stream', image);
}
