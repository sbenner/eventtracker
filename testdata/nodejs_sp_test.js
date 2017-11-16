
var assert = require('assert');
var nock = require('nock');
var querystring = require('querystring');
var snowplowTracker = require('./index');
var tracker = snowplowTracker.tracker;
var emitter = snowplowTracker.emitter;
var version = snowplowTracker.version;

var endpoint = 'localhost:8082/v1/import';

var context = [{
    schema: 'iglu:com.acme/user/jsonschema/1-0-0',
    data: {
        type: 'tester'
    }
}];

function checkPayload(payloadDict, expected) {
    for (var key in expected) {
        assert.strictEqual(expected[key], payloadDict[key]);
    }
    assert.deepEqual(payloadDict['co'], completedContext, 'a custom context should be attached');
    assert.ok(payloadDict['dtm'], 'a timestamp should be attached');
    assert.ok(payloadDict['eid'], 'a UUID should be attached');
}



function getMock(method) {

    return nock('localhost:8082/v1/import')
        .matchHeader('content-type', 'application/json; charset=utf-8')
        .filteringRequestBody(function () {return '*'})
        .post('/com.snowplowanalytics.snowplow/tp2', '*')
        .reply(200, function(uri, body){
            return body;
        });

}

var expected = {
    tv: 'node-' + version,
    tna: 'cf',
    aid: 'cfe35',
    p: 'srv',
    e: 'pv',
    url: 'http://www.example.com',
    page: 'example page',
    refr: 'google'
};

var e = emitter(endpoint, 'http', null, 'post', 0, function (error, body, response) {
    console.log(error);
    console.log(body);
    checkPayload(response.data[0], expected);
});

var t = tracker(e, 'cf', 'cfe35', false);

var mock = getMock('post');
t.trackPageView('http://www.example.com', 'example page', 'google', context);