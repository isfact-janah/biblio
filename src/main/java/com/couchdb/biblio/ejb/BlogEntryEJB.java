package com.couchdb.biblio.ejb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.carauktion.general.couchdb.CouchDBFile;
import ch.carauktion.general.couchdb.CouchDBFileDao;

import com.couchdb.biblio.entity.BlogEntry;
 
@Stateless
public class BlogEntryEJB {
	@PersistenceContext(unitName = "caPU")
	private EntityManager	em;
	
	@Inject
	private CouchDBFileDao couchDBFileDao;
 
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
        CouchDBFile image =   couchDBFileDao.find(id);
            
       return new DefaultStreamedContent(new ByteArrayInputStream(image.getFile()));
	}
}
