const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

const uri = "mongodb+srv://dbAdmin:STt5hkRHc87d6XId@cluster0-q5zeg.mongodb.net/test?retryWrites=true&w=majority";
const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true});

const dbName = 'Cluster0';

client.connect(err => {
  assert.equal(null, err);
  console.log("Connected correctly to server");

  const db = client.db(dbName);
  //const collection = client.db("test").collection("devices");
  // perform actions on the collection object

  client.close();
});
