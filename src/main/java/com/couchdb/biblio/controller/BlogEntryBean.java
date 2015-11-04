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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ch.carauktion.general.couchdb.CouchDBFile;
import ch.carauktion.general.couchdb.CouchDBFileService;

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
	
	@Inject
	private CouchDBFileService couchDBFileService ;

	private BlogEntry blogEntry = new BlogEntry();
	private CouchDBFile file = new CouchDBFile();
	
	
	public void upload(FileUploadEvent event) {
		file = new CouchDBFile();
		if(event != null){
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        
       
        try {
        	 UploadedFile  fileUpload =  (UploadedFile) event.getFile();
			    byte[] foto = IOUtils.toByteArray(fileUpload.getInputstream());
			    file.setCreatedDate(new Date());
		        file.setName(fileUpload.getFileName());
		        file.setUpdatedDate(new Date());
		        file.setFile(foto);
		        file.setMimeType(ch.carauktion.general.couchdb.MimeType.GIF);
		} catch (IOException e) {
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


	public CouchDBFile saveCouchDBFile(CouchDBFile couchDBFile) {
		CouchDBFile image =   couchDBFileService.saveCouchDBFile(couchDBFile);
		return image;
	}
	
	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		 if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	    String id = context.getExternalContext().getRequestParameterMap().get("imageId");
        CouchDBFile image =   couchDBFileService.getCouchDBFileById(id);
            
       return new DefaultStreamedContent(new ByteArrayInputStream(image.getFile()));
	        }
	}

	public BlogEntryEJB getBlogEntryEJB() {
		return blogEntryEJB;
	}

	public void setBlogEntryEJB(BlogEntryEJB blogEntryEJB) {
		this.blogEntryEJB = blogEntryEJB;
	}

	public CouchDBFile getFile() {
		return file;
	}

	public void setFile(CouchDBFile file) {
		this.file = file;
	}
	

	
}