/**
 * 
 */
/**
 * @author nabil
 *
 */
package com.couchdb.biblio.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.couchdb.biblio.ejb.BlogEntryEJB;
import com.couchdb.biblio.entity.BlogEntry;

@ManagedBean(name="blogEntryBean")
@javax.faces.bean.SessionScoped
public class BlogEntryBean {
	
	@PostConstruct
	public void init(){
		System.out.println("*****************init**************");
	}
	
	@Inject
	private BlogEntryEJB blogEntryEJB;

	private BlogEntry blogEntry = new BlogEntry();
	private CouchCBFile file = new CouchCBFile();
	
	
	public void upload(FileUploadEvent event) {
		file = new CouchCBFile();
		if(event != null){
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        
       
        try {
        	 UploadedFile  fileUpload =  (UploadedFile) event.getFile();
			    byte[] foto = IOUtils.toByteArray(fileUpload.getInputstream());
			    file.setCreatedDate(new Date());
		        file.setName(fileUpload.getFileName());
		        file.setUpdatedDate(new Date());
		        file.setImage(foto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		}
    }
	
	/**
	 * @return the blogEntries
	 */
	public List<BlogEntry> getBlogEntries() {
		return blogEntryEJB.findBlogEntries();
	}

	/**
	 * @return the blogEntry
	 */
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	/**
	 * @param blogEntry
	 *            the blogEntry to set
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	public String saveBlogEntry() {
		saveCouchDBFile(file);
		blogEntry.setFileid(file.getId());
		blogEntryEJB.saveBlogEntry(blogEntry);
		return "success";
	}

	public void delete(BlogEntry blogEntry) {
		blogEntryEJB.deleteBlogEntry(blogEntry);
	}

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

	public String saveCouchDBFile(CouchCBFile file) {
		HttpClient httpClient = new StdHttpClient.Builder().host("localhost")
				.port(5984).username("admin").password("password").build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector("couchdbImages", dbInstance);

		db.createDatabaseIfNotExists();
		db.create(file);
		return file.getId();
	}

	public CouchCBFile getFile() {
		return file;
	}

	public void setFile(CouchCBFile file) {
		this.file = file;
	}
}