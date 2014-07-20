package com.couchdb.biblio.controller;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/** 'test' package with 'test/test.png' on the path */
@RequestScoped
@ManagedBean(name="imageBean")
public class ImageBean
{
    private DefaultStreamedContent content;

    public StreamedContent getContent()
    {
        if(content == null)
        {

            HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
					.port(5984).username("admin").password("password").build();
            FacesContext context = FacesContext.getCurrentInstance();
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			CouchDbConnector db = new StdCouchDbConnector("couchdbImages", dbInstance);
			 String id = "772a0f871bed4c0d5a6ee470e300660a";//context.getExternalContext().getRequestParameterMap().get("imageId");
			CouchCBFile image = db.get(CouchCBFile.class, id);
            byte[] bytes = image.getImage();
            System.out.println("Bytes -> " + bytes.length);
            
           
            
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/png", "test.png");
        }

        return content;
    }
}
