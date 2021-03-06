var express = require('express');
var app = express();
var path = require('path');
var router = require('./server/routes/routes.js')
var bodyParser = require('body-parser');


app.use('/inboundURLbase',router)
app.use(express.static(path.join(__dirname, './bower_components')));
app.use(bodyParser.json());

// Serve back static files
app.use(express.static(path.join(__dirname, './client')));

// Handle index file separately
app.get('/', function(req, res) {
  res.sendFile(path.join(__dirname, '.client/index.html'));
})

app.set('port', process.env.PORT || 5000);
app.listen(app.get('port'), function() {
    console.log('Listening on port: ', app.get('port'));
});
