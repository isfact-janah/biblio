package ch.carauktion.general.couchdb;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.ektorp.support.CouchDbDocument;

@JsonWriteNullProperties(false)
@JsonIgnoreProperties({ "id", "revision","newEntity" })
public class CouchDbBaseEntity  extends  CouchDbDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("_id")
	private String id;

	@JsonProperty("_rev")
	private String revision;
	
	@JsonProperty("createddate")
	private Date createddate;
	
	@JsonProperty("updateddate")
	private Date updateddate;
	
	@JsonIgnore
	public boolean newEntity ;
	
	
	public boolean isNewEntity() {
		return id == null || id.equals("");
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id;
	}

	public String getRevision() {
		return revision;
	}
	
	public Date getCreateddate() {
		if(createddate == null){
			return  new Date();
		}else{
			return createddate;
		}
	}

	public void setCreatedDate(Date createdDate) {
		this.createddate = createdDate;
	}

	public Date getUpdatedDate() {
		return new Date();
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updateddate = updatedDate;
	}
	

}