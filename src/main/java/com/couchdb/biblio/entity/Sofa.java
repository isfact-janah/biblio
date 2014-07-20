package com.couchdb.biblio.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.CouchDbDocument;

public class Sofa extends CouchDbDocument {
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String color;
	private Date createdDate;
	private Date updatedDate;


	public void setColor(String s) {
		color = s;
	}

	public String getColor() {
		return color;
	}

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
		return new Date();
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	
}
