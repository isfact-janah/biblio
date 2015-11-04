package ch.carauktion.general.couchdb;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class CouchDBFile extends  CouchDbBaseEntity implements Serializable{
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getSmallFile() {
		return smallFile;
	}

	public void setSmallFile(byte[] smallFile) {
		this.smallFile = smallFile;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public ch.carauktion.general.couchdb.MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(ch.carauktion.general.couchdb.MimeType mimeType) {
		this.mimeType = mimeType;
	}

	private String name;
	private byte[] file;

	private byte[] smallFile;

	private Float width;

	private Float height;

	private Double fileSize;

	private Integer sortOrder;

	private ch.carauktion.general.couchdb.MimeType mimeType;
	 
    
   


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
