package com.couchdb.biblio.controller;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.CouchDbDocument;

public class CouchCBFile extends  CouchDbDocument implements Serializable{
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	private byte[] image;
	private String name;
	private Date createdDate;
	private Date updatedDate;
	 
    
    public Date getCreatedDate() {
		if(createdDate == null){
			return  new Date();
		}else{
			return createdDate;
		}
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		updatedDate = new Date();
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
