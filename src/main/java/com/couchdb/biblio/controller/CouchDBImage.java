package com.couchdb.biblio.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@ApplicationScoped
public class CouchDBImage {
	
	
 public StreamedContent getImage() throws IOException {
		 
		 HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
					.port(5984).username("admin").password("password").build();

			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			CouchDbConnector db = new StdCouchDbConnector("couchdbImages", dbInstance);

	        FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
	            String id = context.getExternalContext().getRequestParameterMap().get("imageId");
	            CouchCBFile image = db.get(CouchCBFile.class, id);
	            
	            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImage()));
	        }
	    }

}
