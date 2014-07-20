package com.couchdb.biblio.controller;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.couchdb.biblio.entity.Sofa;

public class Ektorp {
	public static void main(String[] args) {
      HttpClient httpClient = new StdHttpClient.Builder()
              .host("localhost")
              .port(5984)
              .username("admin")
              .password("password")
              .build();

      CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
      CouchDbConnector db = new StdCouchDbConnector("testdb", dbInstance);

      db.createDatabaseIfNotExists();
      Sofa sofa_ =  new Sofa();
      sofa_.setColor("red"); 
      db.create(sofa_);
      String id = sofa_.getId();
      System.out.println("Sofa ID  :" + sofa_.getId());
      
      Sofa sofa = db.get(Sofa.class, id);
   
      if (sofa.getColor().equals("red")) {
          sofa.setColor("green");
      } else {
          sofa.setColor("red");
      }
      db.update(sofa);
      
      db.delete(sofa);
  }
}


