const express = require("express");
const server = express();

const body_parser = require("body-parser");

const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');
const uri = "mongodb+srv://dbAdmin:STt5hkRHc87d6XId@cluster0-q5zeg.mongodb.net/test?retryWrites=true&w=majority";
const dbName = 'Cluster0';
const collectionName = 'testCol';


server.use(body_parser.json());
server.use(body_parser.urlencoded({ extended: true }));


MongoClient.connect(uri, {useUnifiedTopology: true}, function (err, dbInstance) {
  if (err) {
    throw (err)
  } else {
  //.then(client =>{
    const db = dbInstance.db('test')

    server.post("/test", (request, response) => {
      const itemToAdd = request.body; //get the item to add
      db.collection(collectionName).insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
        if (error) throw error;
        //return updated list
        db.collection(collectionName).find().toArray((_error, _result) => {//callback of find
          if (_error) throw _error;
          response.json(_result);
        }); //end of find callback
      }); //end of insertOne callback
    }); //End of post function

    //get gets items
    server.get("/test/:id", (request, response) => {
      const itemToGet = request.params.id; //Get item to get from request

      db.collection(collectionName).findOne({ id: itemToGet }, (error, result) => { //findOne callback
        if (error) throw error;
        //return item
        response.json(result);
      }); //end of findOne callback
    }); //end of get function

    server.get("/test", async (request, response) => {
      //res.json({message: 'pass!'})

      // return updated list
      db.collection(collectionName).find().toArray((error, result) => {
          if (error) throw error;
          response.json(result);
      });
    });

    //put replaces items in collection
    server.put("/test/:id", (request, response) => {
      const itemId = request.params.id;
      const item = request.body;

      //console.log("Editing item: ", itemId, " to be ", item);

      db.collection(collectionName).updateOne({ id: itemId }, { $set: item }, (error, result) => {
        if (error) throw error;
        //send back entire update list, to make sure frontend data is up-to-data
        db.collection(collectionName).find().toArray(function (_error, _result) {
          if (_error) throw _error;
          response.json(_result);
        });
      });
    }); //end put

    server.delete("/test/:id", (request, response) => {
      const itemId = request.params.id;
      //console.log("Delete item with id: ", itemId);

      db.collection(collectionName).deleteOne({ id: itemId }, function (error, result) {
        if (error) throw error;
        //send back entire updated list after successful request
        db.collection(collectionName).find().toArray(function (_error, _result) {
          if (_error) throw _error;
          response.json(_result);
        });
      });
    }); //end delete
  }
});
//server.listen(4000)
  module.exports = server
