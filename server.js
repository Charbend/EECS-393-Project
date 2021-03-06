/*
EECS 393 Project Server.js
Author: Drew Heald
*/

const express = require("express");
const server = express();

const body_parser = require("body-parser");

//For parsing JSON
server.use(body_parser.json());
server.use(body_parser.urlencoded({ extended: true }));

const port = 4000;

// setting up database
const db = require("./db");
const dbName = "data";
var dbObject;

db.initialize(dbName, function (dbObject) { //successCallback of initialize
  console.log("successful connection");

/************************************************************************** Routes for user collection **************************************************************************/

  //post adds items to collection
  server.post("/users", (request, response) => {
    const itemToAdd = request.body; //get the item to add
    dbObject.collection('users').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
      if (error) throw error;
      //return updated list
      dbObject.collection('users').find().toArray((_error, _result) => {//callback of find
        if (_error) throw _error;
        response.json(_result);
      }); //end of find callback
    }); //end of insertOne callback
  }); //End of post function

  //get users document specified by email in HTTP request
  server.get("/users/:email", (request, response) => {
    const itemToGet = request.params.email; //Get item to get from request

    dbObject.collection('users').findOne({ email: itemToGet }, (error, result) => { //findOne callback
      if (error) throw error;
      //return item
      response.json(result);
    }); //end of findOne callback
  }); //end of get function

  //get all documents in users collection
  server.get("/users", (request, response) => {
    // return updated list
    dbObject.collection('users').find().toArray((error, result) => {
        if (error) throw error;
        response.json(result);
    });
  });

  //update users document, specified by email in HTTP request
  server.put("/users/:email", (request, response) => {
    const itemKey = request.params.email;
    const item = request.body;

    console.log("Editing item: ", itemKey, " to be ", item);

    dbObject.collection('users').updateOne({ email: itemKey }, { $set: item }, (error, result) => {
      if (error) throw error;
      //send back entire update list, to make sure frontend data is up-to-data
      dbObject.collection('users').find().toArray(function (_error, _result) {
        if (_error) throw _error;
        response.json(_result);
      });
    });
  }); //end put

  //Delete users document, specified by email in HTTP request
  server.delete("/users/:email", (request, response) => {
    const itemKey = request.params.email;
    console.log("Delete users with email: ", itemKey);

    dbObject.collection('users').deleteOne({ email: itemKey }, function (error, result) {
      if (error) throw error;
      //send back entire updated list after successful request
      dbObject.collection('users').find().toArray(function (_error, _result) {
        if (_error) throw _error;
        response.json(_result);
      });
    });
  }); //end delete


/************************************************************************** Routes for room collection **************************************************************************/


//post adds items to collection
server.post("/rooms", (request, response) => {
  const itemToAdd = request.body; //get the item to add
  dbObject.collection('rooms').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
    if (error) throw error;
    //return updated list
    dbObject.collection('rooms').find().toArray((_error, _result) => {//callback of find
      if (_error) throw _error;
      response.json(_result);
    }); //end of find callback
  }); //end of insertOne callback
}); //End of post function

//get rooms document specified by listName in HTTP request
server.get("/rooms/:room", (request, response) => {
  const itemToGet = request.params.room; //Get item to get from request

  dbObject.collection('rooms').findOne({ room: itemToGet }, (error, result) => { //findOne callback
    if (error) throw error;
    //return item
    response.json(result);
  }); //end of findOne callback
}); //end of get function

//get all documents in rooms collection
server.get("/rooms", (request, response) => {
  // return updated list
  dbObject.collection('rooms').find().toArray((error, result) => {
      if (error) throw error;
      response.json(result);
  });
});

//update rooms document, specified by listName in HTTP request
server.put("/rooms/:room", (request, response) => {
  const itemKey = request.params.room;
  const item = request.body;

  console.log("Editing item: ", itemKey, " to be ", item);

  dbObject.collection('rooms').updateOne({ room: itemKey }, { $set: item }, (error, result) => {
    if (error) throw error;
    //send back entire update list, to make sure frontend data is up-to-data
    dbObject.collection('rooms').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end put

//Delete rooms document, specified by listName in HTTP request
server.delete("/rooms/:room", (request, response) => {
  const itemKey = request.params.listName;
  console.log("Delete rooms with room: ", itemKey);

  dbObject.collection('rooms').deleteOne({ room: itemKey }, function (error, result) {
    if (error) throw error;
    //send back entire updated list after successful request
    dbObject.collection('rooms').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end delete

/************************************************************************** Routes for lists collection **************************************************************************/


//post adds items to collection
server.post("/lists", (request, response) => {
  const itemToAdd = request.body; //get the item to add
  dbObject.collection('lists').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
    if (error) throw error;
    //return updated list
    dbObject.collection('lists').find().toArray((_error, _result) => {//callback of find
      if (_error) throw _error;
      response.json(_result);
    }); //end of find callback
  }); //end of insertOne callback
}); //End of post function

//get lists document specified by list in HTTP request
server.get("/lists/:list", (request, response) => {
  const itemToGet = request.params.list; //Get item to get from request

  dbObject.collection('lists').findOne({ list: itemToGet }, (error, result) => { //findOne callback
    if (error) throw error;
    //return item
    response.json(result);
  }); //end of findOne callback
}); //end of get function

//get all documents in lists collection
server.get("/lists", (request, response) => {
  // return updated list
  dbObject.collection('lists').find().toArray((error, result) => {
      if (error) throw error;
      response.json(result);
  });
});

//update lists document, specified by list in HTTP request
server.put("/lists/:list", (request, response) => {
  const itemKey = request.params.list;
  const item = request.body;

  console.log("Editing item: ", itemKey, " to be ", item);

  dbObject.collection('lists').updateOne({ list: itemKey }, { $set: item }, (error, result) => {
    if (error) throw error;
    //send back entire update list, to make sure frontend data is up-to-data
    dbObject.collection('lists').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end put

//Delete lists document, specified by list in HTTP request
server.delete("/lists/:list", (request, response) => {
  const itemKey = request.params.list;
  console.log("Delete lists with list: ", itemKey);

  dbObject.collection('lists').deleteOne({ list: itemKey }, function (error, result) {
    if (error) throw error;
    //send back entire updated list after successful request
    dbObject.collection('lists').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end delete

/************************************************************************** Routes for items collection **************************************************************************/

//post adds items to collection
server.post("/items", (request, response) => {
  const itemToAdd = request.body; //get the item to add
  dbObject.collection('items').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
    if (error) throw error;
    //return updated list
    dbObject.collection('items').find().toArray((_error, _result) => {//callback of find
      if (_error) throw _error;
      response.json(_result);
    }); //end of find callback
  }); //end of insertOne callback
}); //End of post function

//get items document specified by itemName in HTTP request
server.get("/items/:itemName", (request, response) => {
  const itemToGet = request.params.itemName; //Get item to get from request

  dbObject.collection('items').findOne({ itemName: itemToGet }, (error, result) => { //findOne callback
    if (error) throw error;
    //return item
    response.json(result);
  }); //end of findOne callback
}); //end of get function

//get all documents in items collection
server.get("/items", (request, response) => {
  // return updated list
  dbObject.collection('items').find().toArray((error, result) => {
      if (error) throw error;
      response.json(result);
  });
});

//update items document, specified by itemName in HTTP request
server.put("/items/:itemName", (request, response) => {
  const itemKey = request.params.itemName;
  const item = request.body;

  console.log("Editing item: ", itemKey, " to be ", item);

  dbObject.collection('items').updateOne({ itemName: itemKey }, { $set: item }, (error, result) => {
    if (error) throw error;
    //send back entire update list, to make sure frontend data is up-to-data
    dbObject.collection('items').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end put

//Delete items document, specified by itemName in HTTP request
server.delete("/items/:itemName", (request, response) => {
  const itemKey = request.params.itemName;
  console.log("Delete items with itemName: ", itemKey);

  dbObject.collection('items').deleteOne({ itemName: itemKey }, function (error, result) {
    if (error) throw error;
    //send back entire updated list after successful request
    dbObject.collection('items').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end delete

/************************************************************************** Routes for pricebookitems collection **************************************************************************/

//post adds pricebookitems to collection
server.post("/pricebookitems", (request, response) => {
  const itemToAdd = request.body; //get the item to add
  dbObject.collection('pricebookitems').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
    if (error) throw error;
    //return updated list
    dbObject.collection('pricebookitems').find().toArray((_error, _result) => {//callback of find
      if (_error) throw _error;
      response.json(_result);
    }); //end of find callback
  }); //end of insertOne callback
}); //End of post function

//get pricebookitems document specified by itemName in HTTP request
server.get("/pricebookitems/:itemName", (request, response) => {
  const itemToGet = request.params.itemName; //Get item to get from request

  dbObject.collection('pricebookitems').findOne({ itemName: itemToGet }, (error, result) => { //findOne callback
    if (error) throw error;
    //return item
    response.json(result);
  }); //end of findOne callback
}); //end of get function

//get all documents in items collection
server.get("/pricebookitems", (request, response) => {
  // return updated list
  dbObject.collection('pricebookitems').find().toArray((error, result) => {
      if (error) throw error;
      response.json(result);
  });
});

//update items document, specified by itemName in HTTP request
server.put("/pricebookitems/:itemName", (request, response) => {
  const itemKey = request.params.itemName;
  const item = request.body;

  console.log("Editing pricebookitems: ", itemKey, " to be ", item);

  dbObject.collection('pricebookitems').updateOne({ itemName: itemKey }, { $set: item }, (error, result) => {
    if (error) throw error;
    //send back entire update list, to make sure frontend data is up-to-data
    dbObject.collection('pricebookitems').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end put

//Delete items document, specified by itemName in HTTP request
server.delete("/pricebookitems/:itemName", (request, response) => {
  const itemKey = request.params.itemName;
  console.log("Delete pricebookitems with itemName: ", itemKey);

  dbObject.collection('pricebookitems').deleteOne({ itemName: itemKey }, function (error, result) {
    if (error) throw error;
    //send back entire updated list after successful request
    dbObject.collection('pricebookitems').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end delete



/************************************************************************** Routes for retailers collection **************************************************************************/


//post adds items to collection
server.post("/retailers", (request, response) => {
  const itemToAdd = request.body; //get the item to add
  dbObject.collection('retailers').insertOne(itemToAdd, (error, result) =>{ //callback of insertOne
    if (error) throw error;
    //return updated list
    dbObject.collection('retailers').find().toArray((_error, _result) => {//callback of find
      if (_error) throw _error;
      response.json(_result);
    }); //end of find callback
  }); //end of insertOne callback
}); //End of post function

//get retailers document specified by retailerName in HTTP request
server.get("/retailers/:retailerName", (request, response) => {
  const itemToGet = request.params.retailerName; //Get item to get from request

  dbObject.collection('retailers').findOne({ retailerName: itemToGet }, (error, result) => { //findOne callback
    if (error) throw error;
    //return item
    response.json(result);
  }); //end of findOne callback
}); //end of get function

//get all documents in retailers collection
server.get("/retailers", (request, response) => {
  // return updated list
  dbObject.collection('retailers').find().toArray((error, result) => {
      if (error) throw error;
      response.json(result);
  });
});

//update retailers document, specified by retailerName in HTTP request
server.put("/retailers/:retailerName", (request, response) => {
  const itemKey = request.params.retailerName;
  const item = request.body;

  console.log("Editing item: ", itemKey, " to be ", item);

  dbObject.collection('retailers').updateOne({ retailerName: itemKey }, { $set: item }, (error, result) => {
    if (error) throw error;
    //send back entire update list, to make sure frontend data is up-to-data
    dbObject.collection('retailers').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end put

//Delete retailers document, specified by retailerName in HTTP request
server.delete("/retailers/:retailerName", (request, response) => {
  const itemKey = request.params.retailerName;
  console.log("Delete retailers with retailerName: ", itemKey);

  dbObject.collection('retailers').deleteOne({ retailerName: itemKey }, function (error, result) {
    if (error) throw error;
    //send back entire updated list after successful request
    dbObject.collection('retailers').find().toArray(function (_error, _result) {
      if (_error) throw _error;
      response.json(_result);
    });
  });
}); //end delete


}, function (err){ //failure callback of initialize
  throw (err);
});

server.listen(port, () => {
  console.log('Server listening at ' + port);
});
