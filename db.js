const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

const uri = "mongodb+srv://dbAdmin:STt5hkRHc87d6XId@cluster0-q5zeg.mongodb.net/test?retryWrites=true&w=majority";
const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true});

const dbName = 'Cluster0';

/*
client.connect(err => {
  assert.equal(null, err);
  console.log("Connected correctly to server");

  const db = client.db(dbName);
  //const collection = client.db("test").collection("devices");
  // perform actions on the collection object

  client.close();
});
*/

function initialize(dbName, dbCollectionName, successCallback, failureCallback) { //To be called in server.js
  MongoClient.connect(uri, { useNewUrlParser: true, useUnifiedTopology: true}, function (err, dbInstance) {
    if (err) {
      console.log('[MongoDB connection] ERROR: ${err}');
      failureCallback(err); //Should be "caught" by the calling function
    } else {
      const dbObject = dbInstance.db(dbName);
      const dbCollection = dbObject.collection(dbCollectionName);

      console.log("[MongoDB connection] SUCCESS");
      successCallback(dbCollection);
    }
  });
}

module.exports = { initialize};
