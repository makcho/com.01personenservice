package com.personenservice.model;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongoDAO {
	Person person = new Person();
	String mongoUri = "mongodb+srv://admin:admin@cluster0-bjjtn.mongodb.net/admin";
	String databaseName = "personen";
	String myCollectionName = "newCollection";
	
	MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
	com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(mongoClientURI);
	MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
	
	Document document = new Document();
	MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(myCollectionName);
			
}