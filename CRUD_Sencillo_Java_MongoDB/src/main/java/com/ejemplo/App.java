package com.ejemplo;

import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("mi_basededatos");
        MongoCollection<Document> collection = db.getCollection("usuarios");

        // CREATE
        Document user = new Document("Nombre", "Sebas").append("Email", "sebas@mail.com");
        // Map<String, Object> userMap = Map.of(
        //     "Nombre", "Sebas3",
        //     "Email", "sebas@mail.com",
        //     "Edad", 30,
        //     "Activo", true
        // );
        // Document user = new Document(userMap);


        collection.insertOne(user);
        System.out.println("Usuario insertado.");

        // READ
        Document usuario = collection.find(eq("Nombre", "Sebas")).first();
        System.out.println("Usuario encontrado: " + usuario.toJson());

        // // UPDATE
        collection.updateOne(eq("Nombre", "Sebas"),
        new Document("$set", new Document("Email", "sebas.actualizado@mail.com")));
        System.out.println("Usuario actualizado.");

        // DELETE
        collection.deleteOne(eq("nombre", "Sebas"));
        System.out.println("Usuario eliminado.");

        // mongoClient.close();
        Document usuario2 = collection.find(eq("Nombre", "Sebas")).first();
        System.out.println("Usuario modificado encontrado: " + usuario2.toJson());
        mongoClient.close();
    
    }
}