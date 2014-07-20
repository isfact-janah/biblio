package com.couchdb.biblio.ejb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.couchdb.biblio.controller.CouchCBFile;
import com.couchdb.biblio.entity.BlogEntry;
 
@Stateless
public class BlogEntryEJB {
	@PersistenceContext(unitName = "caPU")
	private EntityManager	em;
 
	public BlogEntry saveBlogEntry(BlogEntry blogEntry) {
		em.persist(blogEntry);
		return blogEntry;
	}
 
	@SuppressWarnings("unchecked")
	public List<BlogEntry> findBlogEntries() {
		final Query query = em.createQuery("SELECT b FROM BlogEntry b");
		List<BlogEntry> entries = query.getResultList();
		if (entries == null) {
			entries = new ArrayList<BlogEntry>();
		}
//		for(BlogEntry  blogEntry: entries){
//			try {
//				blogEntry.setStreamedContent(getImage(blogEntry.getFileid()));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return entries;
	}
	public void deleteBlogEntry(BlogEntry blogEntry) {
		blogEntry = em.merge(blogEntry);
		em.remove(blogEntry);
	}
	
	public StreamedContent getImage(String id) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		DefaultStreamedContent result = new DefaultStreamedContent();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return result;
		}

		   HttpClient httpClient = new StdHttpClient.Builder()
           .host("localhost")
           .port(5984)
           .username("admin")
           .password("password")
           .build();

            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
            CouchDbConnector db = new StdCouchDbConnector("couchdbImages", dbInstance);
            CouchCBFile image = db.get(CouchCBFile.class, id);
            
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImage()));
	}
}
