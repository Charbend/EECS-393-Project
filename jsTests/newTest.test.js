const {MongoClient} = require('mongodb');

describe('CRUD', () => {
  let connection;
  let db;

  beforeAll(async () => {
    connection = await MongoClient.connect("mongodb+srv://dbAdmin:STt5hkRHc87d6XId@cluster0-q5zeg.mongodb.net/test?retryWrites=true&w=majority", {
      useNewUrlParser: true, useUnifiedTopology: true,
    });
    db = await connection.db("test");
  });

  afterAll(async () => {
    await connection.close();
    await db.close();
  });

  it('should insert a doc into collection', async () => {
    const testCol = db.collection('testCol');

    await testCol.deleteMany({id: 'test1'}); //Clear up previous test stuff

    const mockUser = {id: 'test1', name: 'John'};
    await testCol.insertOne(mockUser);

    const insertedUser = await testCol.findOne({id: 'test1'});
    expect(insertedUser.name).toBe(mockUser.name);
  });

  it('should get a specific doc in the collection', async () =>{
    const testCol = db.collection('testCol');

    const insertedUser = await testCol.findOne({id: 'test1'});
    expect(insertedUser.name).toBe('John');
  });

  it('should insert a document, then delete that docuent', async () => {
    const testCol = db.collection('testCol');

    await testCol.deleteMany({id: 'test2'}); //Clear up previous test stuff

    const mockUser = {id: 'test2', name: 'Jim'};
    await testCol.insertOne(mockUser);

    const insertedUser = await testCol.findOne({id: 'test2'});
    expect(insertedUser.name).toBe(mockUser.name); //Confirm document inserted

    await testCol.deleteOne({id: 'test2'});
    const deletedUser = await testCol.findOne({id: 'test2'});
    expect(deletedUser).toBeNull();
  });

  it('should edit an existing document', async () => {
    const testCol = db.collection('testCol');

    await testCol.deleteMany({id: 'test3'}); //Clear up previous test stuff

    const mockUser = {id: 'test3', name: 'Rick'};
    await testCol.insertOne(mockUser);

    const insertedUser = await testCol.findOne({id: 'test3'});
    expect(insertedUser.name).toBe(mockUser.name); //Confirm document inserted

    await testCol.updateOne({id: 'test3'}, {$set: {name: 'Ricky'}});
    const mockUser2 = {id: 'test3', name: 'Ricky'};
    const updatedUser = await testCol.findOne({id: 'test3'});
    expect(updatedUser.name).toBe(mockUser2.name);
  });
});
