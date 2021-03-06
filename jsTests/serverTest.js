/*
EECS 393 Project Server.js
Initial Author: Drew Heald
*/

const express = require("express");
const server = express();

const body_parser = require("body-parser");

//For parsing JSON
server.use(body_parser.json());
server.use(body_parser.urlencoded({ extended: true }));

const port = 4000;

// setting up database
const db = require("../db");
const dbName = "test";
const collectionName = "testCol";

db.initialize(dbName, function (dbCollection) { //successCallback
  //get all items
  dbCollection.collection(collectionName).find().toArray(function (err, result){
    if (err) throw err;
    //console.log(result);

    // << Return response to client >> TO ADD
  });

  //db CRUD routes

  //post adds items to collection
  server.post("/test", (request, response) => {
    const itemToAdd = request.body; //get the item to add
    dbCollection.collection(collectionName).insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
      if (error) throw error;
      //return updated list
      dbCollection.collection(collectionName).find().toArray((_error, _result) => {//callback of find
        if (_error) throw _error;
        response.json(_result);
      }); //end of find callback
    }); //end of insertOne callback
  }); //End of post function

  //get gets items
  server.get("/test/:id", (request, response) => {
    const itemToGet = request.params.id; //Get item to get from request

    dbCollection.collection(collectionName).findOne({ id: itemToGet }, (error, result) => { //findOne callback
      if (error) throw error;
      //return item
      response.json(result);
    }); //end of findOne callback
  }); //end of get function

  server.get("/test", (request, response) => {
    // return updated list
    dbCollection.collection(collectionName).find().toArray((error, result) => {
        if (error) throw error;
        response.json(result);
    });
  });

  //put replaces items in collection
  server.put("/test/:id", (request, response) => {
    const itemId = request.params.id;
    const item = request.body;

    //console.log("Editing item: ", itemId, " to be ", item);

    dbCollection.collection(collectionName).updateOne({ id: itemId }, { $set: item }, (error, result) => {
      if (error) throw error;
      //send back entire update list, to make sure frontend data is up-to-data
      dbCollection.collection(collectionName).find().toArray(function (_error, _result) {
        if (_error) throw _error;
        response.json(_result);
      });
    });
  }); //end put

  server.delete("/test/:id", (request, response) => {
    const itemId = request.params.id;
    //console.log("Delete item with id: ", itemId);

    dbCollection.collection(collectionName).deleteOne({ id: itemId }, function (error, result) {
      if (error) throw error;
      //send back entire updated list after successful request
      dbCollection.collection(collectionName).find().toArray(function (_error, _result) {
        if (_error) throw _error;
        response.json(_result);
      });
    });
  }); //end delete

}, function (err){ //failure callback
  throw (err);
});

/*
server.listen(port, () => {
  console.log("Server listening at port: " + port);
});
*/
module.exports = server
